package com.models.equipments;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ACTest {

    @Test
    public void shouldReturnACStatusAsON() {
        AC on = new AC(Status.ON);
        assertThat(on.toString()).isEqualTo("AC : ON");
    }

    @Test
    public void shouldReturnACStatusAsOff() {
        AC on = new AC(Status.OFF);
        assertThat(on.toString()).isEqualTo("AC : OFF");
    }

    @Test
    public void shouldReturnPowerConsumptionAs10WhenACIsOn() {
        AC on = new AC(Status.ON);
        assertThat(on.getConsumption()).isEqualTo(10);
    }

    @Test
    public void shouldReturnPowerConsumptionAs0WhenACIsOff() {
        AC off = new AC(Status.OFF);
        assertThat(off.getConsumption()).isEqualTo(0);
    }

    @Test
    public void shouldToggleStatus() {
        AC off = new AC(Status.OFF);
        assertThat(off.on()).isEqualTo(new AC(Status.ON));

        AC on = new AC(Status.ON);
        assertThat(on.off()).isEqualTo(new AC(Status.OFF));
    }
}