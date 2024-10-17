package io.github.liquidjoo.simplehiberante.query.ddl.strategy;


import io.github.liquidjoo.simplehiberante.entity.column.EntityColumn;

public interface ColumnOptionGenerateStrategy {

    boolean acceptable(EntityColumn entityColumn);

    String generateColumnOption();
}
