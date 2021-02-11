package com.smart4aviation.app.model;

import com.smart4aviation.app.util.WeightCalculator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Load {

    @Id
    private Integer flightId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Luggage> luggage = new ArrayList<>();

    private Load() {
    }

    public Load(Integer flightId, List<Luggage> luggage) {
        this.flightId = flightId;
        this.luggage = luggage;
    }

    public Integer getBaggageWeightKg() {
        return luggage.stream().filter(lugg -> lugg.getLuggageType() == LuggageType.BAGGAGE)
                .mapToInt(lugg -> WeightCalculator.convertToKg(lugg.getWeight(), lugg.getWeightUnit()))
                .sum();
    }

    public Integer getCargoWeightKg() {
        return luggage.stream().filter(lugg -> lugg.getLuggageType() == LuggageType.CARGO)
                .mapToInt(lugg -> WeightCalculator.convertToKg(lugg.getWeight(), lugg.getWeightUnit()))
                .sum();
    }

    public Integer getBaggageAmt() {
       return luggage.stream()
               .filter(lugg -> lugg.getLuggageType() == LuggageType.BAGGAGE)
               .mapToInt(Luggage::getPieces)
               .sum();
    }

    public Integer getFlightId() {
        return flightId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Load load = (Load) o;
        return flightId.equals(load.flightId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId);
    }
}
