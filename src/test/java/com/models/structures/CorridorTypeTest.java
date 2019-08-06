package com.models.structures;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CorridorTypeTest {

    @Test
    public void nameForMain() {
        assertThat(CorridorType.MAIN.getName()).isEqualTo("Main");
    }

    @Test
    public void nameForSub() {
        assertThat(CorridorType.SUB.getName()).isEqualTo("Sub");
    }
}