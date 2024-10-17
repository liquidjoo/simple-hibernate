package io.github.liquidjoo.simplehiberante.session.hibernate;

import io.github.liquidjoo.simplehiberante.db.JdbcTemplate;
import io.github.liquidjoo.simplehiberante.entity.EntityClass;
import io.github.liquidjoo.simplehiberante.entity.EntityKey;
import io.github.liquidjoo.simplehiberante.entity.column.EntityColumn;
import io.github.liquidjoo.simplehiberante.session.EntityManager;


import java.util.Map;

public class DefaultEntityManager implements EntityManager {

    private final DefaultEntityPersister entityPersister;
    private final JdbcTemplate jdbcTemplate;

    public DefaultEntityManager(DefaultEntityPersister entityPersister, JdbcTemplate jdbcTemplate) {
        this.entityPersister = entityPersister;
        this.jdbcTemplate = jdbcTemplate;
    }

    //    public <T> T find(final Class<T> clazz, final Object id) {
//        EntityKey entityKey = new EntityKey(id, clazz);
//        Object persistenceContextEntity = persistenceContext.getEntity(entityKey);
//        if (persistenceContextEntity != null) {
//            return (T) persistenceContextEntity;
//        }
//
//        EntityClass<T> entityClass = EntityClass.getInstance(clazz);
//        T loadEntity = entityLoader.find(entityClass, id);
//        persistenceContext.addEntity(id, loadEntity, LOADING);
//        return loadEntity;
//    }

    public void persist(final Object entity) {
        EntityColumn entityId = EntityClass.getInstance(entity.getClass())
                .getEntityId();
        Object id = entityId.getFieldValue(entity);
        if (id == null) {
//            persistenceContext.addEntityEntry(entity, SAVING);
            String queryString = entityPersister.insertQueryString(entity);
            Object generatedId = jdbcTemplate.executeInsert(queryString);
            entityId.assignFieldValue(entity, generatedId);
//            persistenceContext.addEntity(generatedId, entity);
            return;
        }

//        if (persistenceContext.getEntity(new EntityKey(id, entity)) != null) {
//            throw new IllegalStateException("이미 영속화되어있는 entity입니다.");
//        }
//        persistenceContext.addEntity(id, entity, SAVING);
//        String queryString = entityPersister.insertQueryString(entity);
//        Object generatedId = jdbcTemplate.executeInsert(queryString);
    }


//    public void merge(final Object entity) {
//        EntityClass<?> entityClass = EntityClass.getInstance(entity.getClass());
//        Object entityId = getNotNullEntityId(entityClass, entity);
//        Map<EntityColumn, Object> changedColumns = getSnapshot(entity, entityId).changedColumns(entity);
//        if (changedColumns.isEmpty()) {
//            return;
//        }
//        persistenceContext.addEntity(entityId, entity);
//        entityPersister.update(entityClass, entityId, changedColumns);
//    }

    private Object getNotNullEntityId(final EntityClass<?> entityClass, final Object entity) {
        Object entityId = entityClass.getEntityId()
                .getFieldValue(entity);
        if (entityId == null) {
            throw new IllegalStateException("id가 없는 entity는 merge할 수 없습니다.");
        }
        return entityId;
    }

//    private EntitySnapshot getSnapshot(final Object entity, final Object entityId) {
//        EntityKey entityKey = new EntityKey(entityId, entity.getClass());
//        EntitySnapshot snapshot = persistenceContext.getDatabaseSnapshot(entityKey);
//        if (snapshot == null) {
//            find(entity.getClass(), entityId);
//            return persistenceContext.getDatabaseSnapshot(entityKey);
//        }
//        return snapshot;
//    }

//
//    public void remove(final Object entity) {
//        persistenceContext.addEntityEntry(entity, DELETED);
//        entityPersister.delete(entity);
//        persistenceContext.removeEntity(entity);
//    }

}
