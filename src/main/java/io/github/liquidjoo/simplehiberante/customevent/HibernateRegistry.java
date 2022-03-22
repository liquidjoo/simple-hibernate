package io.github.liquidjoo.simplehiberante.customevent;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Component
public class HibernateRegistry {

    private final EntityManagerFactory entityManagerFactory;
    private final CustomPostInsertEvent customPostInsertEvent;

    public HibernateRegistry(EntityManagerFactory entityManagerFactory, CustomPostInsertEvent customPostInsertEvent) {
        this.entityManagerFactory = entityManagerFactory;
        this.customPostInsertEvent = customPostInsertEvent;
    }

    @PostConstruct
    private void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);

        EventListenerRegistry registry = sessionFactory
                                            .getServiceRegistry()
                                            .getService(EventListenerRegistry.class);

        registry.getEventListenerGroup(EventType.POST_INSERT)
                .appendListener(customPostInsertEvent);
    }

    // db 트리거아 아니라 세션 기반의 트리거
    @Component
    static class CustomPostInsertEvent implements PostInsertEventListener {
        @Override
        public void onPostInsert(PostInsertEvent event) {

        }

        @Override
        public boolean requiresPostCommitHanding(EntityPersister persister) {
            // deprecated
            return false;
        }

        @Override
        public boolean requiresPostCommitHandling(EntityPersister persister) {
            //
            return PostInsertEventListener.super.requiresPostCommitHandling(persister);
        }
    }
}
