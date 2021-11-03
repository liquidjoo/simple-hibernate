package io.github.liquidjoo.simplehiberante.product;

import io.github.liquidjoo.simplehiberante.annotation.SimpleEntity;

import java.math.BigDecimal;

@SimpleEntity
public class Product {

    private Long id;

    private String name;

    private String type;

    private BigDecimal price;
}
