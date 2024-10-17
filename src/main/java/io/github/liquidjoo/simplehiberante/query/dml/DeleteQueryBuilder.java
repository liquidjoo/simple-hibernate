package io.github.liquidjoo.simplehiberante.query.dml;


import io.github.liquidjoo.simplehiberante.entity.column.EntityColumn;

public class DeleteQueryBuilder {

    public static final DeleteQueryBuilder INSTANCE = new DeleteQueryBuilder();

    private static final String DELETE_QUERY = "delete from %s where %s = %s;";

    private DeleteQueryBuilder() {
    }

    public String generateQuery(
            final String tableName,
            final EntityColumn entityId,
            final Object id
    ) {
        return String.format(DELETE_QUERY, tableName, entityId.getFieldName(), id);
    }
}
