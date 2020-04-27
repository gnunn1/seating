package com.redhat.demo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.redhat.demo.model.Reservation;
import com.redhat.demo.model.ReservationCsvRecord;
import com.redhat.demo.util.ArrayListAggregationStrategy;
import com.redhat.demo.util.BatchSizePredicate;
import com.redhat.demo.util.CsvRecordtoReservationMapper;


@Component
public class LoadFileRoute extends RouteBuilder {

    private static final String QUESTION_MARK = "?";
    private static final String AMPERSAND = "&";
    private static final String COLON = ":";

    @Autowired
    private CsvRecordtoReservationMapper mapper;

    @Value("${camel.batch.timeout}")
    private long batchTimeout;

    @Value("${camel.batch.max.records}")
    private int maxRecords;

    @Value("${source.type}")
    private String sourceType;

    @Value("${source.location}")
    private String sourceLocation;

    @Value("${noop.flag}")
    private boolean isNoop;

    @Value("${recursive.flag}")
    private boolean isRecursive;

    @Value("${file.type}")
    private String fileType;

    @Override
    public void configure() {

        final BindyCsvDataFormat bindyCsvDataFormat = new BindyCsvDataFormat(ReservationCsvRecord.class);
        bindyCsvDataFormat.setLocale("default");

        from(buildFileUrl())
                .unmarshal(bindyCsvDataFormat)
                .split(body())
                .streaming()
                .bean(mapper, "convertAndTransform")
                .marshal().json(JsonLibrary.Jackson)
                .log("Body " + body())
                // .aggregate(constant(true), new ArrayListAggregationStrategy())
                // .completionPredicate(new BatchSizePredicate(maxRecords))
                // .completionTimeout(batchTimeout)
                .end();
    }

    private String buildFileUrl() {
        return sourceType + COLON + sourceLocation +
                QUESTION_MARK + "noop=" + isNoop +
                AMPERSAND + "recursive=" + isRecursive +
                AMPERSAND + "include=" + fileType;
    }
}