package com.models.structures;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FloorTest {

    @Test
    public void shouldCreateFloorWithOneMainCorridor() {
        Floor floor = new Floor(1, 1, 0);

        final Corridor expectedMainCorridor = Corridor.main(1);
        assertThat(floor.getCorridors()).containsExactly(expectedMainCorridor);
    }

    @Test
    public void shouldCreateFloorWithMainAndSubCorridor() {
        Floor floor = new Floor(1, 1, 1);

        final Corridor expectedMainCorridor = Corridor.main(1);
        final Corridor expectedSubCorridor = Corridor.sub(1);
        assertThat(floor.getCorridors()).containsExactlyInAnyOrder(
            expectedMainCorridor, expectedSubCorridor);
    }

    @Test
    public void shouldReturnFloorStatus() {
        Floor floor = new Floor(1, 1, 2);
        assertThat(floor.toString()).isEqualTo(expectedStatus());
    }

    @Test
    public void shouldReturnPowerConsumption() {
        Floor floor = new Floor(1, 1, 1);
        assertThat(floor.getConsumption()).isEqualTo(25);
    }

    @Test
    public void shouldReturnCorridorCount() {
        Floor floor = new Floor(1, 2, 3);
        assertThat(floor.mainCorridorCount()).isEqualTo(2);
        assertThat(floor.subCorridorCount()).isEqualTo(3);
    }

    @Test
    public void shouldReturnTrueIfFloorNumberMatches() {
        Floor floor = new Floor(1, 2, 3);
        assertThat(floor.isFloorWithNumber(1)).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseIfFloorNumberMatches() {
        Floor floor = new Floor(1, 2, 3);
        assertThat(floor.isFloorWithNumber(2)).isEqualTo(false);
    }

    private String expectedStatus() {
        return "Floor 1\n" +
            "Main corridor 1 Light 1: ON AC : ON\n" +
            "Sub corridor 1 Light 1: OFF AC : ON\n" +
            "Sub corridor 2 Light 2: OFF AC : ON";
    }
}