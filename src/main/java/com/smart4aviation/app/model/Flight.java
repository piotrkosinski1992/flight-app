package com.smart4aviation.app.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Flight {

    @Id
    private Integer flightId;
    private Integer flightNumber;
    @Enumerated(EnumType.STRING)
    private ATACode departureAirportIATACode;
    @Enumerated(EnumType.STRING)
    private ATACode arrivalAirportIATACode;
    private Instant departureDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flightId")
    private Load load;

    private Flight() {
    }

    public Flight(Integer flightId,
                  Integer flightNumber,
                  ATACode departureAirportIATACode,
                  ATACode arrivalAirportIATACode,
                  Instant departureDate) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureAirportIATACode = departureAirportIATACode;
        this.arrivalAirportIATACode = arrivalAirportIATACode;
        this.departureDate = departureDate;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public Instant getDepartureDate() {
        return departureDate;
    }

    public ATACode getDepartureAirportIATACode() {
        return departureAirportIATACode;
    }

    public ATACode getArrivalAirportIATACode() {
        return arrivalAirportIATACode;
    }

    public Load getLoad() {
        return load;
    }

    public void setLoad(Load load) {
        this.load = load;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flightId.equals(flight.flightId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId);
    }
}
