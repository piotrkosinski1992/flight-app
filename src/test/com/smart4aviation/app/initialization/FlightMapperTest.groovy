package com.smart4aviation.app.initialization

import spock.lang.Specification

import java.time.Instant

class FlightMapperTest extends Specification {

    private def flightMapper = new FlightMapper()

    def "should convert flightDto to entity model"() {
        given:
        def dto = [
        flightId: 1,
        flightNumber: 5015,
        departureAirportIATACode: "SEA",
        arrivalAirportIATACode: "LAX",
        departureDate: "2018-11-10T11:57:41-01:00"
        ] as FlightDto

        when:
        def entity = flightMapper.convertToEntity(dto)

        then:
        println Instant.now().toString()
        with(entity) {
            flightId == dto.flightId
            flightNumber == dto.flightNumber
            departureAirportIATACode.toString() == dto.departureAirportIATACode
            arrivalAirportIATACode.toString() == dto.arrivalAirportIATACode
            departureDate
        }
    }
}
