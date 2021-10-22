package io.github.liquidjoo.simplehiberante.session;

import io.github.liquidjoo.simplehiberante.session.hibernate.EntityPersister;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    private Map<Class, Map<Long, Object>> identityHashMap = new HashMap<>();
    private Map<String, EntityPersister> entityPersisterMap = new ConcurrentHashMap<>();

    private final DataBase dataBase;

    public SessionManager(final DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void save(Class<?> clazz, Long id, Object object) {
        Map<Long, Object> objectMap = new HashMap<>();
        objectMap.put(id, object);

        identityHashMap.put(clazz, objectMap);
    }

    public Object get(Class<?> clazz, Long id) {
        return identityHashMap.get(id);
    }

    public void detached(Long id) {
        identityHashMap.remove(id);
    }

    public void remove(Long id) {
        dataBase.remove();
    }

    public Object find(Class<?> clazz, Long id) {
        Object object = identityHashMap.get(id);
        if (clazz != object.getClass()) {
            throw new IllegalArgumentException();
        }
        return object;
    }

}

interface DataBase {
    Object select();

    void remove();
}
