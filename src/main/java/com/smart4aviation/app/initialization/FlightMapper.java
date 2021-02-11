package com.smart4aviation.app.initialization;

import com.smart4aviation.app.model.ATACode;
import com.smart4aviation.app.model.Flight;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
class FlightMapper {

    public Flight convertToEntity(FlightDto dto) {
        return new Flight(
                dto.getFlightId(),
                dto.getFlightNumber(),
                ATACode.valueOf(dto.getDepartureAirportIATACode()),
                ATACode.valueOf(dto.getArrivalAirportIATACode()),
                ZonedDateTime.parse(dto.getDepartureDate(), DateTimeFormatter.ISO_DATE_TIME).toInstant()
        );

    }
}
