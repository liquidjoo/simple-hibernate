package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CarTest {

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car(1L, "아반떼", BigDecimal.valueOf(20_000_000L), LocalDate.of(2021, 8, 1));
    }

    @Test
    @DisplayName("reflection 을 사용해 Car 필드값 가져오기")
    void getFields() {
        Class<Car> carClass = Car.class;
        Field[] declaredFields = carClass.getDeclaredFields();

        assertAll("car fields", () -> {
           assertThat(declaredFields[0].getName()).isEqualTo("id");
           assertThat(declaredFields[1].getName()).isEqualTo("name");
           assertThat(declaredFields[2].getName()).isEqualTo("price");
           assertThat(declaredFields[3].getName()).isEqualTo("releaseDate");
        });
    }
}
