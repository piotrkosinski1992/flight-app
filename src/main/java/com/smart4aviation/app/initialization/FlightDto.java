package com.smart4aviation.app.initialization;

class FlightDto {

    private Integer flightId;
    private Integer flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    private String departureDate;

    public Integer getFlightId() {
        return flightId;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureAirportIATACode() {
        return departureAirportIATACode;
    }

    public String getArrivalAirportIATACode() {
        return arrivalAirportIATACode;
    }

    public String getDepartureDate() {
        return departureDate;
    }
}
