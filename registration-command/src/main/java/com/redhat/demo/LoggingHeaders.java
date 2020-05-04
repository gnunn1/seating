package com.redhat.demo;

import org.apache.camel.Exchange;
import org.apache.camel.spi.HeaderFilterStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingHeaders implements HeaderFilterStrategy {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingHeaders.class);

    public LoggingHeaders() {
    }

    @Override
    public boolean applyFilterToCamelHeaders(String headerName, Object headerValue, Exchange exchange) {
        String value = (headerValue==null)?"null":"("+headerValue.getClass().getName()+"): "+ headerValue.toString();
        LOGGER.info("Camel Header: " + headerName + ":" + value);
        return false;
    }

    @Override
    public boolean applyFilterToExternalHeaders(String headerName, Object headerValue, Exchange exchange) {
        String value = (headerValue==null)?"null":"("+headerValue.getClass().getName()+"): "+ headerValue.toString();
        LOGGER.info("External Header: " + headerName + ":" + value);
        return false;
    }
}