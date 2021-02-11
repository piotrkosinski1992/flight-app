package com.smart4aviation.app.repo;

import com.smart4aviation.app.model.ATACode;
import com.smart4aviation.app.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    Optional<Flight> findByFlightNumberAndDepartureDateAfterAndDepartureDateBefore(Integer flightNumber, Instant from, Instant to);

    @Query("SELECT f FROM Flight f WHERE (f.departureAirportIATACode = ?1 or f.arrivalAirportIATACode= ?1) and (f.departureDate BETWEEN ?2 AND ?3)")
    List<Flight> findFlightsBy(ATACode ataCode, Instant from, Instant to);
}
