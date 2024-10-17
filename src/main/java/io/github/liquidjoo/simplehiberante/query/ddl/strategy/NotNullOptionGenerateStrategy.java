package io.github.liquidjoo.simplehiberante.query.ddl.strategy;


import io.github.liquidjoo.simplehiberante.entity.column.EntityColumn;

public class NotNullOptionGenerateStrategy implements ColumnOptionGenerateStrategy {

    private static final String NOT_NULL_COLUMN_OPTION = "not null";

    @Override
    public boolean acceptable(final EntityColumn entityColumn) {
        return !entityColumn.isNullable();
    }

    @Override
    public String generateColumnOption() {
        return NOT_NULL_COLUMN_OPTION;
    }
}
