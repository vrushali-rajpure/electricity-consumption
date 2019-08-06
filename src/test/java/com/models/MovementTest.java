package com.models;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class MovementTest {

    @Test
    public void isActiveMovement() {
        Movement movement = Movement.newMovement(1, 1);
        assertThat(movement.isActive()).isTrue();
    }

    @Test
    public void isExpiredMovement() {

        Movement movement = Movement.noMovement(1, 1);
        assertThat(movement.isActive()).isFalse();
    }
}