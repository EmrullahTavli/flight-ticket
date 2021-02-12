package com.finartz.flightticket.web.exception;

public class FlightException extends RuntimeException {
    public FlightException() {
        super();
    }

    public FlightException(String message) {
        super(message);
    }
}
