package com.models;

import com.models.structures.Floors;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FloorResourceMonitorTest {

    @Test
    public void shouldTurnOnSubcorridorLightOnMovement() {

        Floors floors = Floors.createFloors(1, 1, 2);
        FloorResourceMonitor monitor = new FloorResourceMonitor(floors);

        Movement movement = Movement.newMovement(1, 2);
        monitor.applyMovement(movement);

        String expectedFloorState = "Floor 1\n" +
            "Main corridor 1 Light 1: ON AC : ON\n" +
            "Sub corridor 1 Light 1: OFF AC : OFF\n" +
            "Sub corridor 2 Light 2: ON AC : ON";

        // TODO : not the best way to test
        assertThat(monitor.getFloors().getStatus()).isEqualTo(expectedFloorState);
    }

    @Test
    public void shouldTurnNonActiveCorridorACLightOffWhenConsumptionThresholdIsReached() {

        Floors floors = Floors.createFloors(1, 1, 2);
        FloorResourceMonitor monitor = new FloorResourceMonitor(floors);

        Movement oldMovement = Movement.newMovement(1, 2);
        monitor.applyMovement(oldMovement);

        Movement newMovement = Movement.newMovement(1, 1);
        monitor.applyMovement(newMovement);

        String expectedFloorState = "Floor 1\n" +
            "Main corridor 1 Light 1: ON AC : ON\n" +
            "Sub corridor 1 Light 1: ON AC : ON\n" +
            "Sub corridor 2 Light 2: OFF AC : OFF";

        // TODO : not the best way to test
        assertThat(monitor.getFloors().getStatus()).isEqualTo(expectedFloorState);
    }

    @Test
    public void shouldRestoreStateInCaseOfNoMovement() {

        Floors floors = Floors.createFloors(1, 1, 2);
        FloorResourceMonitor monitor = new FloorResourceMonitor(floors);

        Movement oldMovement = Movement.newMovement(1, 2);
        monitor.applyMovement(oldMovement);

        Movement newMovement = Movement.noMovement(1, 2);
        monitor.applyMovement(newMovement);

        String expectedFloorState = "Floor 1\n" +
            "Main corridor 1 Light 1: ON AC : ON\n" +
            "Sub corridor 1 Light 1: OFF AC : ON\n" +
            "Sub corridor 2 Light 2: OFF AC : ON";

        // TODO : not the best way to test
        assertThat(monitor.getFloors().getStatus()).isEqualTo(expectedFloorState);
    }

}