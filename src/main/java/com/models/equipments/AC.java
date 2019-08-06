package com.models.equipments;

import lombok.EqualsAndHashCode;

import static com.models.equipments.Status.OFF;
import static com.models.equipments.Status.ON;

@EqualsAndHashCode
public class AC implements IConsumption, IEquipement {
    private final int POWER_CONSUMPTION = 10;
    private Status status;

    public AC(Status status) {
        this.status = status;
    }

    @Override public String toString() {
        return "AC : " + status;
    }

    @Override public int getConsumption() {
        return status == Status.ON ? POWER_CONSUMPTION : 0;
    }

    @Override public AC on() {
        this.status = ON;
        return this;
    }

    @Override public AC off() {
        this.status = OFF;
        return this;
    }
}
