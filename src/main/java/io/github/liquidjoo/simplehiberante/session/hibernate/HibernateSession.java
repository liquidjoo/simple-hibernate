package io.github.liquidjoo.simplehiberante.session.hibernate;

import java.io.Serializable;
import java.util.Optional;

public class HibernateSession implements EventSource {
    private MetamodelImpl metamodel;

    public <T> T load(Class<T> entityClass, Serializable id) {
        return this.byId(entityClass).load(id);
    }

    private EntityPersister locateEntityPersister(Class entityClass) {
//            return getFactory().getMetamodel().locateEntityPersister( entityClass );
        return metamodel.locateEntityPersister(entityClass);
    }

    public <T> IdentifierLoadAccessImpl<T> byId(Class<T> entityClass) {
        return new IdentifierLoadAccessImpl<T>(entityClass);
    }

    @Override
    public void persist(String entityName, Object object) {
        firePersist(new PersistEvent( entityName, object, this ));
    }

    private void firePersist(final PersistEvent event) {

    }

    private class IdentifierLoadAccessImpl<T> implements IdentifierLoadAccess<T> {
        private final EntityPersister entityPersister;

        private IdentifierLoadAccessImpl(EntityPersister entityPersister) {
            this.entityPersister = entityPersister;
        }

        private IdentifierLoadAccessImpl(Class<T> entityClass) {
            this(locateEntityPersister(entityClass));
        }

        @Override
        public final T load(Serializable id) {
            return doLoad(id);
        }

        @Override
        public Optional<T> loadOptional(Serializable id) {
            return Optional.ofNullable(doLoad(id));
        }

        @SuppressWarnings("unchecked")
        protected final T doLoad(Serializable id) {

            LoadEvent event = new LoadEvent(id, entityPersister.getEntityName(), false, HibernateSession.this);
            boolean success = false;
            try {
                fireLoad(event, LoadEventListener.GET);
                success = true;
            } catch (Exception e) {
                // if io.github.liquidjoo.simplehiberante.session cache contains proxy for non-existing object
            } finally {
                afterOperation(success);
            }
            return (T) event.getResult();
        }

        private void fireLoad(LoadEvent loadEvent, LoadEventListener.LoadType loadType) {
            //

        }

        public void afterOperation(boolean success) {

        }

        AbstractEntityPersister -> select(데이터 바인딩 이후) ->
        -> sql 저장소 -> db access -> 시리얼라이즈 -> 객체 -> EntitiyPersister;
    }
}
