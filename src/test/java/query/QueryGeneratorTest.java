package query;

import db.H2InMemory;
import model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class QueryGeneratorTest {

    private Car car;
    private H2InMemory h2InMemory;

    @BeforeEach
    void setUp() {
        car = new Car(1L, "아반떼", BigDecimal.valueOf(20_000_000L), LocalDate.of(2021, 8, 1));
        h2InMemory = new H2InMemory();
    }

    @Test
    @DisplayName("CREATE 쿼리 만들어보기")
    void create() {
        h2InMemory.executeByQuery("");
    }

    @Test
    @DisplayName("ALTER 쿼리 만들어보기")
    void alter() {

    }

    @Test
    @DisplayName("DROP 쿼리 만들어보기")
    void drop() {
    }

    @Test
    @DisplayName("TRUNCATE 쿼리 만들어보기")
    void truncate() {
    }

    @Test
    @DisplayName("SELECT 쿼리 만들어보기")
    void select() {
    }

    @Test
    @DisplayName("INSERT 쿼리 만들어보기")
    void insert() {
    }

    @Test
    @DisplayName("UPDATE 쿼리 만들어보기")
    void update() {
    }

    @Test
    @DisplayName("DELETE 쿼리 만들어보기")
    void delete() {
    }
}
