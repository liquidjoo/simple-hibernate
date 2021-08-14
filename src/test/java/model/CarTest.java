package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class CarTest {

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car(1L, "아반떼", BigDecimal.valueOf(20_000_000L), LocalDate.of(2021, 8, 1));
    }

    @Test
    @DisplayName("reflection 을 사용해 Car 필드값 가져오기")
    void name() {

    }
}
