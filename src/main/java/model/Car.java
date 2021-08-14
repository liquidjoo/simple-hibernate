package model;

import annotation.Access;
import annotation.AccessType;
import annotation.Entity;

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
}
