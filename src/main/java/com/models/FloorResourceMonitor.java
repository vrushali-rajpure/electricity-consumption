package com.models;

import com.models.structures.Corridor;
import com.models.structures.Floor;
import com.models.structures.Floors;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Getter;

//TODO : pubsub model ?
public class FloorResourceMonitor {

    private final int MAIN_CORRIDOR_CONSUMPTION_LIMIT = 15;
    private final int SUB_CORRIDOR_CONSUMPTION_LIMIT = 10;
    @Getter
    private Floors floors;

    public FloorResourceMonitor(Floors floors) {
        this.floors = floors;
    }

    public void applyMovement(Movement movement) {

        final int corridorNumber = movement.getCorridorNumber();

        final Floor floorWithMovement = floorWithMovement(movement.getFloorNumber());
        updateCorridorWithActiveMovement(subCorridorWithMovement(floorWithMovement, corridorNumber), movement);

        final List<Corridor> withoutMovement =
            subCorridorWithoutActiveMovement(floorWithMovement, movement);
        for (Corridor corridor : withoutMovement) {
            updateCorridorWithoutActiveMovement(corridor, movement);
            if (PowerConsumptionEvaluator.isResourceUtilizationCrossed(floorWithMovement)) {
                saveConsumption(corridor);
            }
        }
    }

    //TODO : move to something else?
    private void updateCorridorWithoutActiveMovement(Corridor eachCorridor, Movement movement) {
        if (movement.isActive()) {
            saveConsumption(eachCorridor);
        } else {
            eachCorridor.turnACOn();
        }
    }

    private void saveConsumption(Corridor eachCorridor) {
        eachCorridor.turnLightOff();
        eachCorridor.turnACOff();
    }

    private void updateCorridorWithActiveMovement(Corridor corridorWithMovement, Movement movement) {
        corridorWithMovement.turnACOn();
        if (movement.isActive()) {
            corridorWithMovement.turnLightOn();
        } else {
            corridorWithMovement.turnLightOff();
        }
    }

    private Floor floorWithMovement(int movementFloor) {
        final Optional<Floor> optionalFloor = floors.getFloorWithNumber(movementFloor);

        if (!optionalFloor.isPresent()) {
            throw new IllegalArgumentException("Floor number is invalid :" + movementFloor);
        }

        return optionalFloor.get();
    }

    private List<Corridor> subCorridorWithoutActiveMovement(
        Floor floor, Movement movement) {
        return floor
            .subCorridors()
            .stream()
            .filter(subCorridor -> !isActiveMovement(subCorridor.getCorridorNumber(), movement))
            .collect(Collectors.toList());
    }

    private boolean isActiveMovement(int corridorNumber, Movement movement) {

        // TODO : can this be moved to Movement?
        return movement.getCorridorNumber() == corridorNumber
            && movement.isActive();
    }

    private Corridor subCorridorWithMovement(Floor floor, int corridorNumber) {

        final Optional<Corridor> optionalCorridor = floor
            .subCorridors()
            .stream()
            .filter(corridor -> corridor.isCorridorWithNumber(corridorNumber))
            .findFirst();

        if (!optionalCorridor.isPresent()) {
            throw new IllegalArgumentException("Corridor number is invalid :" + corridorNumber);
        }

        return optionalCorridor.get();
    }
}
