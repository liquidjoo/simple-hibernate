package io.github.liquidjoo.simplehiberante.query.ddl.strategy;


import io.github.liquidjoo.simplehiberante.entity.column.EntityColumn;

import javax.persistence.GenerationType;

public class IdIdentityOptionGenerateStrategy implements ColumnOptionGenerateStrategy {

    private static final String AUTO_INCREMENT_COLUMN_OPTION = "auto_increment";

    @Override
    public boolean acceptable(final EntityColumn entityColumn) {
        return entityColumn.isId() && entityColumn.getGenerationType() == GenerationType.IDENTITY;
    }

    @Override
    public String generateColumnOption() {
        return AUTO_INCREMENT_COLUMN_OPTION;
    }
}
