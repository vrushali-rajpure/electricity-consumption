package com.models.equipments;

import lombok.EqualsAndHashCode;

import static com.models.equipments.Status.OFF;
import static com.models.equipments.Status.ON;

@EqualsAndHashCode
public class Light implements IConsumption, IEquipement {
    private final int POWER_CONSUMPTION = 5;
    private final int number;
    private Status status;

    private Light(int number, Status status) {
        this.number = number;
        this.status = status;
    }

    public static Light on(int number) {
        return new Light(number, Status.ON);
    }

    public static Light off(int number) {
        return new Light(number, OFF);
    }

    @Override public String toString() {
        return "Light " + number + ": " + status;
    }

    @Override public int getConsumption() {
        return status == Status.ON ? POWER_CONSUMPTION : 0;
    }

    @Override public Light on() {
        this.status = ON;
        return this;
    }

    @Override public Light off() {
        this.status = OFF;
        return this;
    }
}
