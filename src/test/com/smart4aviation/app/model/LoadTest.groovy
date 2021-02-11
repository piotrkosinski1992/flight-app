package com.smart4aviation.app.model

import spock.lang.Specification

class LoadTest extends Specification {

    private def testLoad = [
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

    def "should return baggage weight in kg"() {
        expect:
        testLoad.getBaggageWeightKg() == 445
    }

    def "should return cargo weight in kg"() {
        expect:
        testLoad.getCargoWeightKg() == 145
    }

    def "should return baggage amount"() {
        expect:
        testLoad.getBaggageAmt() == 40
    }
}
