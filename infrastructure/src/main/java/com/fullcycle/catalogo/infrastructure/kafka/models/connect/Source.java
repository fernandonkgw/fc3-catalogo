package com.fullcycle.catalogo.infrastructure.kafka.models.connect;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Source(
        @JsonProperty("name") String name,
        @JsonProperty("db") String db,
        @JsonProperty("table") String table
) {
}
