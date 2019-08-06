package com.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Movement {

    @Getter
    private final int floorNumber;
    @Getter
    private final int corridorNumber;
    private boolean isMovement;

    private Movement(boolean isMovement, int floorNumber, int corridorNumber) {
        this.isMovement = isMovement;
        this.floorNumber = floorNumber;
        this.corridorNumber = corridorNumber;
    }

    static Movement newMovement(int floorNumber, int corridorNumber) {
        return new Movement(true, floorNumber, corridorNumber);
    }

    static Movement noMovement(int floorNumber, int corridorNumber) {
        return new Movement(false, floorNumber, corridorNumber);
    }

    public static Movement getMovement(boolean isMovement, int floorNumber, int subCorridorNumber) {
        return isMovement ?
            newMovement(floorNumber, subCorridorNumber)
            : noMovement(floorNumber, subCorridorNumber);
    }

    public boolean isActive() {
        return isMovement;
    }
}
