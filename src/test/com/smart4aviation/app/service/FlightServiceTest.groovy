package com.smart4aviation.app.service

import com.smart4aviation.app.model.*
import com.smart4aviation.app.repo.FlightRepository
import spock.lang.Specification

import java.time.Instant
import java.time.LocalDate

class FlightServiceTest extends Specification {

    private def testFlight = [
            flightId                : 1234,
            flightNumber            : 5443,
            departureAirportIATACode: ATACode.GDN,
            arrivalAirportIATACode  : ATACode.ANC,
            departureDate           : Instant.now(),
            load                    : [
                    flightId: 1234,
                    luggage : [
                            [
                                    id         : 1,
                                    weight     : 400,
                                    weightUnit : "kg",
                                    pieces     : 20,
                                    luggageType: LuggageType.BAGGAGE
                            ] as Luggage,
                            [
                                    id         : 8,
                                    weight     : 100,
                                    weightUnit : "lb",
                                    pieces     : 20,
                                    luggageType: LuggageType.BAGGAGE
                            ] as Luggage,
                            [
                                    id         : 2,
                                    weight     : 100,
                                    weightUnit : "lb",
                                    pieces     : 10,
                                    luggageType: LuggageType.CARGO
                            ] as Luggage,
                            [
                                    id         : 2,
                                    weight     : 100,
                                    weightUnit : "kg",
                                    pieces     : 10,
                                    luggageType: LuggageType.CARGO
                            ] as Luggage
                    ] as List<Luggage>
            ] as Load
    ] as Flight

    private def flightRepository = Mock(FlightRepository)
    private def flightService = new FlightService(flightRepository)

    def "should thrown exception when flight is not found"() {
        given:
        def flightNumber = 1234
        def date = LocalDate.of(2002, 6, 6)
        def dateRange = new InstantDayRange(date)
        flightRepository.findByFlightNumberAndDepartureDateAfterAndDepartureDateBefore(flightNumber, dateRange.getFrom(), dateRange.getTo()) >> Optional.empty()

        when:
        flightService.getWeightInfoBy(flightNumber, date)

        then:
        thrown(FlightNotFound)
    }

    def "should get weight info by given data"() {
        given:
        def flightNumber = 1234
        def date = LocalDate.of(2002, 6, 6)
        def dateRange = new InstantDayRange(date)
        flightRepository.findByFlightNumberAndDepartureDateAfterAndDepartureDateBefore(flightNumber, dateRange.getFrom(), dateRange.getTo()) >> Optional.of(testFlight)

        when:
        def result = flightService.getWeightInfoBy(flightNumber, date)

        then:
        with(result) {
            cargoWeight == 145
            baggageWeight == 445
            totalWeight == 590
        }
    }

    def "should get flight info by given data"() {
        given:
        def ataCode = ATACode.GDN
        def date = LocalDate.of(2002, 6, 6)
        def dateRange = new InstantDayRange(date)
        flightRepository.findFlightsBy(ataCode, dateRange.getFrom(), dateRange.getTo()) >> [testFlight]

        when:
        def result = flightService.getFlightInfo(ataCode, date)

        then:
        with(result) {
            departuresAmt == 1
            arrivalsAmt == 0
            departingBaggageAmt == 40
            arrivingBaggageAmt == 0
        }

    }
}
