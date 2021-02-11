package com.smart4aviation.app.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Luggage {

    @Id
    private Integer id;
    private Integer weight;
    private String weightUnit;
    private Integer pieces;
    @Enumerated(EnumType.STRING)
    private LuggageType luggageType;

    private Luggage() {
    }

    public Luggage(Integer id, Integer weight, String weightUnit, Integer pieces, LuggageType luggageType) {
        this.id = id;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.pieces = pieces;
        this.luggageType = luggageType;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public Integer getPieces() {
        return pieces;
    }

    public LuggageType getLuggageType() {
        return luggageType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Luggage luggage = (Luggage) o;
        return id.equals(luggage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
