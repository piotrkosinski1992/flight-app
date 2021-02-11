package com.smart4aviation.app.initialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart4aviation.app.model.Flight;
import com.smart4aviation.app.model.Load;
import com.smart4aviation.app.repo.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
class DataInitializer implements CommandLineRunner {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final LoadMapper loadMapper;

    public DataInitializer(FlightRepository flightRepository,
                           FlightMapper flightMapper,
                           LoadMapper loadMapper) {
        this.flightRepository = flightRepository;;
        this.flightMapper = flightMapper;
        this.loadMapper = loadMapper;
    }

    @Override
    public void run(String... args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Flight> flights = prepareFlights(mapper);
        List<Load> loads = prepareLoads(mapper);

        flights.forEach(flight -> flight.setLoad(findLoadByFlightId(loads, flight.getFlightId())));
        flightRepository.saveAll(flights);
    }

    private List<Flight> prepareFlights(ObjectMapper mapper) throws IOException {
        List<FlightDto> flightDtos = mapper.readValue(new File("target/classes/flights.json"), new TypeReference<>() {
        });
        return flightDtos.stream()
                .map(flightMapper::convertToEntity)
                .collect(Collectors.toList());
    }

    private List<Load> prepareLoads(ObjectMapper mapper) throws IOException {
        List<LoadDto> loadDtos = mapper.readValue(new File("target/classes/loads.json"), new TypeReference<>() {
        });
        return loadDtos.stream()
                .map(loadMapper::convertToEntity)
                .collect(Collectors.toList());
    }

    private Load findLoadByFlightId(List<Load> loads, Integer flightId) {
        return loads.stream().filter(load -> load.getFlightId().equals(flightId))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("No matching load information for flight %s", flightId)));
    }
}
