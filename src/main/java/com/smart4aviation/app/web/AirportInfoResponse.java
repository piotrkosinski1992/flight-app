package com.smart4aviation.app.web;

public class AirportInfoResponse {
    private final Integer departuresAmt;
    private final Integer arrivalsAmt;
    private final Integer departingBaggageAmt;
    private final Integer arrivingBaggageAmt;

    public AirportInfoResponse(Integer departuresAmt, Integer arrivalsAmt, Integer departingBaggageAmt, Integer arrivingBaggageAmt) {
        this.departuresAmt = departuresAmt;
        this.arrivalsAmt = arrivalsAmt;
        this.departingBaggageAmt = departingBaggageAmt;
        this.arrivingBaggageAmt = arrivingBaggageAmt;
    }

    public Integer getDeparturesAmt() {
        return departuresAmt;
    }

    public Integer getArrivalsAmt() {
        return arrivalsAmt;
    }

    public Integer getDepartingBaggageAmt() {
        return departingBaggageAmt;
    }

    public Integer getArrivingBaggageAmt() {
        return arrivingBaggageAmt;
    }
}
