package io.github.liquidjoo.simplehiberante.product;

import io.github.liquidjoo.simplehiberante.annotation.SimpleColumn;
import io.github.liquidjoo.simplehiberante.annotation.SimpleEntity;
import io.github.liquidjoo.simplehiberante.annotation.SimpleId;

import java.math.BigDecimal;

@SimpleEntity
public class Product {

    @SimpleId
    private Long id;

    @SimpleColumn
    private String name;

    @SimpleColumn
    private String type;

    @SimpleColumn
    private BigDecimal price;
}
