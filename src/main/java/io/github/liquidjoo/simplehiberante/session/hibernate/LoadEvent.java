package io.github.liquidjoo.simplehiberante.session.hibernate;

import java.io.Serializable;

public class LoadEvent extends AbstractEvent{

    private Serializable entityId;
    private String entityClassName;
    private Object instanceToLoad;
//    private LockOptions lockOptions;
    private boolean isAssociationFetch;
    private Object result;
//    private PostLoadEvent postLoadEvent;

    public LoadEvent(Serializable entityId, String entityClassName, boolean isAssociationFetch, EventSource source) {
        this(entityId, entityClassName, null, isAssociationFetch, source);

    }

    private LoadEvent(Serializable entityId, String entityClassName, Object instanceToLoad, boolean isAssociationFetch, EventSource source) {
        super(source);

        if ( entityId == null ) {
            throw new IllegalArgumentException( "id to load is required for loading" );
        }

        this.entityId = entityId;
        this.entityClassName = entityClassName;
        this.instanceToLoad = instanceToLoad;
        this.isAssociationFetch = isAssociationFetch;
    }

    public Object getResult() {
        return result;
    }

    static class DandockPrice {

    }
}
