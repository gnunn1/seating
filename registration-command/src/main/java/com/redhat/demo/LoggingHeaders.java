package com.redhat.demo;

import java.nio.charset.StandardCharsets;

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
        LOGGER.info("Camel Header: " + headerName + ":" + getHeaderValue(headerValue));
        return false;
    }

    @Override
    public boolean applyFilterToExternalHeaders(String headerName, Object headerValue, Exchange exchange) {
        LOGGER.info("External Header: " + headerName + ":" + getHeaderValue(headerValue));
        return false;
    }

    private String getHeaderValue(Object headerValue) {
        if (headerValue != null) {
            try {
                byte[] bytes = (byte[])headerValue;
                if (bytes != null) {
                    String value = new String(bytes, StandardCharsets.UTF_8);
                    return "(byte[]): " + value;
                }
            } catch (Throwable t) {
                LOGGER.error("Error when trying to convert bytes to string", t);
            }
            return "("+headerValue.getClass().getName()+"): "+ headerValue.toString();
        } else {
            return "null";
        }
    }
}