package com.smart4aviation.app.service;

import java.time.LocalDate;

public class FlightNotFound extends RuntimeException {
    public FlightNotFound(Integer flightNumber, LocalDate date) {
        super(String.format("Flight not found for given criteria: %s %s", flightNumber, date.toString()));
    }
}
