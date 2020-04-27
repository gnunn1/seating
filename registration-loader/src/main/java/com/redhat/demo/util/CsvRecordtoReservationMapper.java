package com.redhat.demo.util;

import com.redhat.demo.model.Reservation;
import com.redhat.demo.model.ReservationCsvRecord;
import org.springframework.stereotype.Component;

@Component
public class CsvRecordtoReservationMapper {

    public Reservation convertAndTransform(ReservationCsvRecord csvRecord) {
        final Reservation reservation = new Reservation(
                csvRecord.getName(),
                csvRecord.getEmail(),
                csvRecord.getSeatName(),
                csvRecord.getSeatNo());
        //log.info("Converting ({}) into ({})", csvRecord, person);
        return reservation;
    }

}
