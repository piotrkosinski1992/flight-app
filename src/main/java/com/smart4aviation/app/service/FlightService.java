package com.smart4aviation.app.service;

import com.smart4aviation.app.model.InstantDayRange;
import com.smart4aviation.app.web.WeightInfoResponse;
import com.smart4aviation.app.model.ATACode;
import com.smart4aviation.app.model.Flight;
import com.smart4aviation.app.repo.FlightRepository;
import com.smart4aviation.app.web.AirportInfoResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public WeightInfoResponse getWeightInfoBy(Integer flightNumber, LocalDate date) {
        InstantDayRange range = new InstantDayRange(date);
        Flight flight = flightRepository
                .findByFlightNumberAndDepartureDateAfterAndDepartureDateBefore(flightNumber, range.getFrom(), range.getTo())
                .orElseThrow(() -> new FlightNotFound(flightNumber, date));
        return new WeightInfoResponse(flight.getLoad().getCargoWeightKg(), flight.getLoad().getBaggageWeightKg());
    }

    public AirportInfoResponse getFlightInfo(ATACode ataCode, LocalDate date) {
        InstantDayRange range = new InstantDayRange(date);

        List<Flight> flights = flightRepository.findFlightsBy(ataCode, range.getFrom(), range.getTo());

        return new AirportInfoResponse(
                (int) getDeparturesStreamBy(flights, ataCode).count(),
                (int) getArrivalsStreamBy(flights, ataCode).count(),
                calculateBaggageAmt(getDeparturesStreamBy(flights, ataCode)),
                calculateBaggageAmt(getArrivalsStreamBy(flights, ataCode)));
    }

    private Stream<Flight> getArrivalsStreamBy(List<Flight> flights, ATACode ataCode) {
        return flights.stream()
                .filter(flight -> flight.getArrivalAirportIATACode() == ataCode);
    }

    private Stream<Flight> getDeparturesStreamBy(List<Flight> flights, ATACode ataCode) {
        return flights.stream()
                .filter(flight -> flight.getDepartureAirportIATACode() == ataCode);
    }

    private Integer calculateBaggageAmt(Stream<Flight> flightStream) {
        return flightStream.mapToInt(flight -> flight.getLoad().getBaggageAmt()).sum();
    }
}
