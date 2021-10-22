package io.github.liquidjoo.simplehiberante.session;

import io.github.liquidjoo.simplehiberante.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.liquidjoo.simplehiberante.query.QueryGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {
    private SessionManager manager;
    private int removeCount = 0;
    private int selectCount = 0;

    @BeforeEach
    void setUp() {
        manager = new SessionManager(new DataBase() {
            @Override
            public Object select() {
                selectCount++;
                return null;
            }

            @Override
            public void remove() {
                removeCount++;
            }
        });
    }

    @Test
    void save() {
        final Car car = new Car(1L, "김성주", BigDecimal.ZERO, LocalDate.now());
        manager.save(Car.class, car.getId(), car);
        assertThat(manager.get(Car.class, car.getId())).isSameAs(car);
    }

    @Test
    void detached() {
        final Car car = new Car(1L, "김성주", BigDecimal.ZERO, LocalDate.now());
        manager.save(Car.class, car.getId(), car);
        manager.detached(car.getId());
        assertThat(manager.get(Car.class, car.getId())).isNull();
    }

    @Test
    void remove() {
        final Car car = new Car(1L, "김성주", BigDecimal.ZERO, LocalDate.now());
        manager.remove(car.getId());
        assertThat(removeCount).isEqualTo(1);
    }

    @Test
    void find() {
        final Car car = (Car) manager.find(Car.class, 1L);
        assertThat(selectCount).isEqualTo(1);
    }
}
