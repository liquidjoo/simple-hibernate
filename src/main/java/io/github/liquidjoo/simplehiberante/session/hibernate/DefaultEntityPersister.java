package io.github.liquidjoo.simplehiberante.session.hibernate;

import io.github.liquidjoo.simplehiberante.entity.EntityClass;
import io.github.liquidjoo.simplehiberante.entity.column.EntityColumn;
import io.github.liquidjoo.simplehiberante.query.dml.DeleteQueryBuilder;
import io.github.liquidjoo.simplehiberante.query.dml.InsertQueryBuilder;
import io.github.liquidjoo.simplehiberante.query.dml.UpdateQueryBuilder;

import java.util.Map;

public class DefaultEntityPersister implements EntityPersister{

    private final InsertQueryBuilder insertQueryBuilder = InsertQueryBuilder.INSTANCE;
    private final DeleteQueryBuilder deleteQueryBuilder = DeleteQueryBuilder.INSTANCE;
    private final UpdateQueryBuilder updateQueryBuilder = UpdateQueryBuilder.INSTANCE;

    public String  updateQueryString(final EntityClass<?> entityClass, final Object entityId, final Map<EntityColumn, Object> updateFields) {
        final String query = updateQueryBuilder.generateQuery(
                entityClass.tableName(),
                updateFields,
                entityClass.getEntityId(),
                entityId
        );
        return query;
    }

    public String insertQueryString(final Object entity) {
        EntityClass<?> entityClass = EntityClass.getInstance(entity.getClass());
        final String query = insertQueryBuilder.generateQuery(
                entityClass.tableName(),
                entityClass.getFieldValues(entity)
        );

        return query;
    }

    public String deleteQueryString(final Object entity) {
        EntityClass<?> entityClass = EntityClass.getInstance(entity.getClass());
        EntityColumn entityId = entityClass.getEntityId();
        final String query = deleteQueryBuilder.generateQuery(
                entityClass.tableName(),
                entityId,
                entityId.getFieldValue(entity)
        );
        return query;
    }

}
