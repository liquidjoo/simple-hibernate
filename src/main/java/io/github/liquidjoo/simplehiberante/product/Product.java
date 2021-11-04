package io.github.liquidjoo.simplehiberante.product;

import io.github.liquidjoo.simplehiberante.annotation.SimpleColumn;
import io.github.liquidjoo.simplehiberante.annotation.SimpleEntity;
import io.github.liquidjoo.simplehiberante.annotation.SimpleId;
import io.github.liquidjoo.simplehiberante.annotation.SimpleTransient;

import java.math.BigDecimal;

@SimpleEntity
public class Product {

    @SimpleId
    private Long id;

    @SimpleColumn
    private String name;

    @SimpleColumn
    private String productType;

    @SimpleTransient
    private BigDecimal price;

    public Product() {

    }

    public Product(Long id, String name, String productType, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.productType = productType;
        this.price = price;
    }
}
