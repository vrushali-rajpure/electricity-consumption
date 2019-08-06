package com.models.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Floors {

    private List<Floor> floors;

    private Floors(List<Floor> floors) {
        this.floors = floors;
    }

    public static Floors createFloors(
        int floorsCount, int mainCorridorsCount, int subCorridorCount) {
        List<Floor> floorList = new ArrayList<>(floorsCount);
        int index = 0;
        while (index < floorsCount) {
            floorList.add(new Floor(index + 1, mainCorridorsCount, subCorridorCount));
            index++;
        }
        return new Floors(floorList);
    }

    public Optional<Floor> getFloorWithNumber(int floorNumber) {
        return floors
            .stream()
            .filter(floor -> floor.isFloorWithNumber(floorNumber))
            .findFirst();
    }

    public String getStatus() {
        return floors
            .stream()
            .map(Floor::toString)
            .reduce((a, b) -> a + "\n" + b)
            .orElse("");
    }

}
