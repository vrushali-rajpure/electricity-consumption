package com.models;

import com.models.structures.Floor;
import com.models.structures.Floors;
import java.util.Optional;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FloorsTest {

    @Test
    public void shouldReturnFloorStatus() {
        final Floors floors = Floors.createFloors(1, 1, 2);
        final String floorsStatus = floors.getStatus();
        assertThat(floorsStatus).isEqualTo(expectedStatus());
    }

    @Test
    public void shouldReturnFloorWithNumber() {
        final Floors floors = Floors.createFloors(2, 1, 2);
        final Optional<Floor> floor = floors.getFloorWithNumber(2);
        Floor expectedFloor = new Floor(2, 1, 2);

        assertThat(floor.isPresent()).isEqualTo(true);
        assertThat(floor.get()).isEqualTo(expectedFloor);
    }

    private String expectedStatus() {
        StringBuilder builder = new StringBuilder("");
        builder.append("Floor 1\n");
        builder.append("Main corridor 1 Light 1: ON AC : ON\n");
        builder.append("Sub corridor 1 Light 1: OFF AC : ON\n");
        builder.append("Sub corridor 2 Light 2: OFF AC : ON");
        return builder.toString();
    }
}