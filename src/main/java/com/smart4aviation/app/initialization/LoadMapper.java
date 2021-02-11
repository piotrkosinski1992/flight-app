package com.smart4aviation.app.initialization;

import com.smart4aviation.app.model.Load;
import com.smart4aviation.app.model.Luggage;
import com.smart4aviation.app.model.LuggageType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class LoadMapper {

    public Load convertToEntity(LoadDto dto) {
        return new Load(
                dto.getFlightId(),
                prepareLuggageCollection(dto)
        );
    }

    private List<Luggage> prepareLuggageCollection(LoadDto dto) {
        List<Luggage> luggageList = new ArrayList<>();

        dto.getBaggage().forEach(baggage -> luggageList.add(new Luggage(
                baggage.getId(),
                baggage.getWeight(),
                baggage.getWeightUnit(),
                baggage.getPieces(),
                LuggageType.BAGGAGE
        )));

        dto.getCargo().forEach(cargo -> luggageList.add(new Luggage(
                cargo.getId(),
                cargo.getWeight(),
                cargo.getWeightUnit(),
                cargo.getPieces(),
                LuggageType.CARGO
        )));

        return luggageList;
    }
}
