package com.models.structures;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CorridorTest {

    @Test
    public void shouldReturnStatusOfMainCorridor() {
        final Corridor main = Corridor.main(1);
        assertThat(main.toString()).isEqualTo("Main corridor 1 Light 1: ON AC : ON");
    }

    @Test
    public void shouldReturnStatusOfSubCorridor() {
        final Corridor sub = Corridor.sub(2);
        String expectedStatus = "Sub corridor 2 Light 2: OFF AC : ON";
        assertThat(sub.toString()).isEqualTo(expectedStatus);
    }

    @Test
    public void shouldReturnPowerConsumptionForMainCorridor() {
        final Corridor main = Corridor.main(1);
        assertThat(main.getConsumption()).isEqualTo(15);
    }

    @Test
    public void shouldReturnPowerConsumptionForSubCorridor() {
        final Corridor sub = Corridor.sub(1);
        assertThat(sub.getConsumption()).isEqualTo(10);
    }

    @Test
    public void shouldReturnTrueForMainCorridor() {
        final Corridor main = Corridor.main(1);
        assertThat(main.isMain()).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseForSubCorridor() {
        final Corridor sub = Corridor.sub(1);
        assertThat(sub.isMain()).isEqualTo(false);
    }
}