package com.smart4aviation.app.web;

import com.smart4aviation.app.service.FlightService;
import com.smart4aviation.app.model.ATACode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/weight-info/{flightNumber}")
    private WeightInfoResponse getWeightsBy(@PathVariable Integer flightNumber,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return flightService.getWeightInfoBy(flightNumber, date);
    }

    @GetMapping("/airport-info/{ataCode}")
    private AirportInfoResponse getWeightsBy(@PathVariable ATACode ataCode,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return flightService.getFlightInfo(ataCode, date);
    }
}
