package com.models.structures;

public enum CorridorType {

    MAIN("Main"), SUB("Sub");

    private final String name;

    CorridorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
