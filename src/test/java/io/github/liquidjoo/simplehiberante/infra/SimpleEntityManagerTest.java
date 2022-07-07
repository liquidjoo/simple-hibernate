package io.github.liquidjoo.simplehiberante.infra;

import io.github.liquidjoo.simplehiberante.product.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SimpleEntityManagerTest {

    @Test
    void name() throws IllegalAccessException {
        SimpleEntityManager entityManager = new SimpleEntityManager();
        entityManager.firstCache(Product.class, 1L);

        entityManager.updateSession(Product.class, 1L, new Product(1L, "justin", "mouse", BigDecimal.valueOf(40_000)));

//        entityManager.loadSession()


    }
}
