package com.smart4aviation.app.initialization;

import java.util.List;

class LoadDto {

    private Integer flightId;
    private List<LuggageDto> baggage;
    private List<LuggageDto> cargo;

    public Integer getFlightId() {
        return flightId;
    }

    public List<LuggageDto> getBaggage() {
        return baggage;
    }

    public List<LuggageDto> getCargo() {
        return cargo;
    }
}
