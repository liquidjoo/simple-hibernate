package io.github.liquidjoo.simplehiberante.model;

import io.github.liquidjoo.simplehiberante.annotation.Access;
import io.github.liquidjoo.simplehiberante.annotation.AccessType;
import io.github.liquidjoo.simplehiberante.annotation.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Access(AccessType.FIELD)
@Entity
public class Car {

    private Long id;

    private String name;

    private BigDecimal price;

    private LocalDate releaseDate;

    public Car(Long id, String name, BigDecimal price, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }
}
