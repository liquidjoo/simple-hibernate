package io.github.liquidjoo.simplehiberante.session.hibernate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MetamodelImpl {

    // 이미 맵은 완성되어 있는 상태 (초기화 완료)
    private final Map<String,EntityPersister> entityPersisterMap = new ConcurrentHashMap<>();

    private final Map<Class,String> entityProxyInterfaceMap = new ConcurrentHashMap<>();

    public EntityPersister locateEntityPersister(Class byClass) {
        EntityPersister entityPersister = entityPersisterMap.get( byClass.getName() );
        if ( entityPersister == null ) {
            String mappedEntityName = entityProxyInterfaceMap.get( byClass );
            if ( mappedEntityName != null ) {
                entityPersister = entityPersisterMap.get( mappedEntityName );
            }
        }

        if ( entityPersister == null ) {
            throw new IllegalArgumentException( "Unable to locate persister: " + byClass.getName() );
        }

        return entityPersister;
    }
}
