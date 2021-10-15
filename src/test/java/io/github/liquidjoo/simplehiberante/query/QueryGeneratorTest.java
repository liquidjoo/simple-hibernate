package io.github.liquidjoo.simplehiberante.query;

import io.github.liquidjoo.simplehiberante.db.H2Dialect;
import io.github.liquidjoo.simplehiberante.db.H2InMemory;
import io.github.liquidjoo.simplehiberante.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        List<String> rows = new ArrayList<>();
        Class<Car> carClass = Car.class;

        Field[] declaredFields = carClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String typeValue = H2Dialect.getTypeValue(H2Dialect.convertType(declaredField.getType()));
            rows.add(declaredField.getName() + " " + typeValue);
        }

        String fields = String.join(",\n", rows);

        String createQuery = QueryGenerator.getCreateQuery(carClass.getSimpleName(), fields);
        System.out.println(createQuery);

        String dropQuery = QueryGenerator.getDropTable(carClass.getSimpleName());

        h2InMemory.executeByQuery(dropQuery);
        h2InMemory.executeByQuery(createQuery);
    }

    @Test
    @DisplayName("ALTER 쿼리 만들어보기")
    void alter() {
        Class<Car> carClass = Car.class;
        String alterQuery = QueryGenerator.getAlterTableByColumnAdd(carClass.getSimpleName(), "tmp", String.class);
        h2InMemory.executeByQuery(alterQuery);
    }

    @Test
    @DisplayName("DROP 쿼리 만들어보기")
    void drop() {
        Class<Car> carClass = Car.class;
        String dropQuery = QueryGenerator.getDropTable(carClass.getSimpleName());
        h2InMemory.executeByQuery(dropQuery);
    }

    @Test
    @DisplayName("TRUNCATE 쿼리 만들어보기")
    void truncate() {
        Class<Car> carClass = Car.class;
        String truncateQuery = QueryGenerator.getTruncateTable(carClass.getSimpleName());
        h2InMemory.executeByQuery(truncateQuery);
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
