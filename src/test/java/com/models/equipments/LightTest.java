package com.models.equipments;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LightTest {

    @Test
    public void shouldReturnACStatusAsON() {
        Light on = Light.on(1);
        assertThat(on.toString()).isEqualTo("Light 1: ON");
    }

    @Test
    public void shouldReturnACStatusAsOff() {
        Light on = Light.off(2);
        assertThat(on.toString()).isEqualTo("Light 2: OFF");
    }

    @Test
    public void shouldReturnPowerConsumptionAs5WhenLightIsOn() {
        Light on = Light.on(1);
        assertThat(on.getConsumption()).isEqualTo(5);
    }

    @Test
    public void shouldReturnPowerConsumptionAs0WhenLightIsOff() {
        Light off = Light.off(1);
        assertThat(off.getConsumption()).isEqualTo(0);
    }

    @Test
    public void shouldToggleStatus() {
        Light off = Light.off(1);
        assertThat(off.on()).isEqualTo(Light.on(1));

        Light on = Light.on(2);
        assertThat(on.off()).isEqualTo(Light.off(2));
    }
}