package com.smart4aviation.app.web;

public class WeightInfoResponse {
    private final Integer cargoWeight;
    private final Integer baggageWeight;
    private final Integer totalWeight;

    public WeightInfoResponse(Integer cargoWeight, Integer baggageWeight) {
        this.cargoWeight = cargoWeight;
        this.baggageWeight = baggageWeight;
        this.totalWeight = cargoWeight + baggageWeight;
    }

    public Integer getCargoWeight() {
        return cargoWeight;
    }

    public Integer getBaggageWeight() {
        return baggageWeight;
    }

    public Integer getTotalWeight() {
        return totalWeight;
    }
}
