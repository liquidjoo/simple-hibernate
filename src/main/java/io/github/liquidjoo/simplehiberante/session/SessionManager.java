package io.github.liquidjoo.simplehiberante.session;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private Map<Class, Map<Long, Object>> identityHashMap = new HashMap<>();
    private Map<String,EntityPersister> entityPersisterMap = new ConcurrentHashMap<>();

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
        return io.github.liquidjoo.simplehiberante.session.get(id);
    }

    public void detached(Long id) {
        io.github.liquidjoo.simplehiberante.session.remove(id);
    }

    public void remove(Long id) {
        dataBase.remove();
    }

    public Object find(Class<?> clazz, Long id) {
        Object object = io.github.liquidjoo.simplehiberante.session.get(id);
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
