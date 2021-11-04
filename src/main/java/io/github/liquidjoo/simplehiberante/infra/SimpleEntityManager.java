package io.github.liquidjoo.simplehiberante.infra;

import io.github.liquidjoo.simplehiberante.product.Product;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SimpleEntityManager {

    Map<Class, Map<Long, Object>> session = new HashMap<>();
    Map<Class, Map<Long, Object>> snapshots = new HashMap<>();

    public SimpleEntityManager() {
//        new Product(1L, "seongju", "cup", BigDecimal.valueOf(1000L));
    }

    public void firstCache(Class clazz, Long id) {
        Object o = session.get(clazz).get(id);
        if (Objects.isNull(o)) {
            // db select
            Map<Long, Object> data = new HashMap<>();
            data.put(1L, new Product(1L, "seongju", "cup", BigDecimal.valueOf(1000L)));
            session.get(clazz).put(id, data);

            Map<Long, Object> snapshotMap = new HashMap<>();
            Product snapshot = new Product(1L, "seongju", "cup", BigDecimal.valueOf(1000L));
            snapshotMap.put(id, snapshot);
            snapshots.get(clazz).put(id, snapshotMap);
        }
    }

    public Product loadSession(Class clazz, Long id) {
        firstCache(clazz, id);

        return (Product) session.get(clazz).get(id);
    }

    public void updateSession(Class clazz, Long id, Product product) throws IllegalAccessException {
        Product o = (Product) snapshots.get(clazz).get(id);
        for (Field declaredField : clazz.getDeclaredFields()) {
            Object preField = declaredField.get(o);
            Object currentField = declaredField.get(product);

            if (!preField.equals(currentField)) {
                declaredField.setAccessible(true);
                declaredField.set(o, currentField);
            }
        }
    }

    static class EntityPersist {
        private Long id;
        private String entityClassName;

        public EntityPersist(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

    }

}
