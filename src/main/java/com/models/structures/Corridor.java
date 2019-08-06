package com.models.structures;

import com.models.equipments.AC;
import com.models.equipments.Light;
import com.models.equipments.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Corridor {
    @Getter
    private final int corridorNumber;
    private final CorridorType type;
    private final AC ac;
    private Light light;

    private Corridor(int corridorNumber, CorridorType type) {
        this.corridorNumber = corridorNumber;
        this.type = type;
        this.ac = new AC(Status.ON);
        this.light = Light.on(corridorNumber);
    }

    public static Corridor main(int corridorNumber) {
        Corridor main = new Corridor(
            corridorNumber, CorridorType.MAIN);
        main.light = Light.on(corridorNumber);
        return main;
    }

    public static Corridor sub(int corridorNumber) {
        Corridor sub = new Corridor(corridorNumber, CorridorType.SUB);
        sub.light = Light.off(corridorNumber);
        return sub;
    }

    @Override public String toString() {
        return type.getName() + " corridor " + corridorNumber +
            " " + light + " " + ac;
    }

    public int getConsumption() {
        return ac.getConsumption() + light.getConsumption();
    }

    public boolean isMain() {
        return type == CorridorType.MAIN;
    }

    public boolean isCorridorWithNumber(int inputCorridorNumber) {
        return corridorNumber == inputCorridorNumber;
    }

    // TODO : not a right place?
    public void turnLightOn() {
        this.light.on();
    }

    public void turnLightOff() {
        this.light.off();
    }

    public void turnACOff() {
        this.ac.off();
    }

    public void turnACOn() {
        this.ac.on();
    }
}
