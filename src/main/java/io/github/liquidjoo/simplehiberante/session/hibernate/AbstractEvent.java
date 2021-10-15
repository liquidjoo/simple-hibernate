package io.github.liquidjoo.simplehiberante.session.hibernate;

import java.io.Serializable;

public abstract class AbstractEvent implements Serializable {

    private final EventSource session;

    public AbstractEvent(EventSource session) {
        this.session = session;
    }

    public EventSource getSession() {
        return session;
    }
}
