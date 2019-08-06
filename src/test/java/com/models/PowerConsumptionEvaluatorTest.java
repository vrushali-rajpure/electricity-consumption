package com.models;

import com.models.structures.Floor;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PowerConsumptionEvaluatorTest {

    @Test
    public void shouldReturnTrueIfPowerConsumptionExceeds() {
        Floor floor = new Floor(1, 1, 2);
        floor.subCorridors().get(0).turnLightOn();
        final boolean utilizationCrossed = PowerConsumptionEvaluator.isResourceUtilizationCrossed(floor);

        assertThat(utilizationCrossed).isTrue();
    }

    @Test
    public void shouldReturnFalseIfPowerConsumptionDoesExceeds() {
        Floor floor = new Floor(1, 1, 2);
        final boolean utilizationCrossed = PowerConsumptionEvaluator.isResourceUtilizationCrossed(floor);

        assertThat(utilizationCrossed).isFalse();
    }
}