package com.fullcycle.catalogo.infrastructure.kafka.models.connect;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Operation {
    CREATE("c"), UPDATE("u"), DELETE("d");

    private final String op;

    Operation(String op) {
        this.op = op;
    }

    @JsonCreator // deserializar json > objeto
    public static Operation of(final String value) {
        return Arrays.stream(values())
                .filter(it -> it.op.equals(value))
                .findFirst()
                .orElse(null);
    }

    @JsonValue // serializa de objeto > json
    public String op() {
        return op;
    }

    public static boolean isDelete(Operation op) {
        return DELETE == op;
    }
}
