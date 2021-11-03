package io.github.liquidjoo.simplehiberante.step1;

import java.math.BigDecimal;

public class TestCar {

    private Long id;

    private String name;

    private BigDecimal price;

    public void testCar1() {
        System.out.println("testCar1 출력 입니다.");
    }

    public void testCar2() {
        System.out.println("testCar2 출력 입니다.");
    }

    public void printCar() {
        System.out.println("printCar 출력 입니다.");
    }

    @ThisIsTest
    public void annotationMethod() {
        System.out.println("ThisIsTest 출력");
    }
}
