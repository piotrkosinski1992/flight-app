package com.smart4aviation.app.model;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class InstantDayRange {
    private final Instant from;
    private final Instant to;

    public InstantDayRange(LocalDate date) {
        from = Instant.from(date.atStartOfDay().atZone(ZoneId.of("UTC")));
        to = from.plus(Duration.ofDays(1));
    }

    public Instant getFrom() {
        return from;
    }

    public Instant getTo() {
        return to;
    }
}
