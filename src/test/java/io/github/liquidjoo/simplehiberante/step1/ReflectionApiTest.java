package io.github.liquidjoo.simplehiberante.step1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionApiTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionApiTest.class);

    @Test
    @DisplayName("클래스 정보 출력")
    void getClassInfo() {
        Class<TestCar> carClass = TestCar.class;
        logger.debug(carClass.getName());
    }

    @Test
    @DisplayName("test로 시작하는 메소드 실행")
    void prefixTestMethod() {
        Class<TestCar> carClass = TestCar.class;
    }

    @Test
    @DisplayName("@ThisIsTest 어노테이션 메소드 실행")
    void thisIsTestMethod() {
        Class<TestCar> carClass = TestCar.class;
    }

    @Test
    @DisplayName("private field에 값 할당")
    void privateFieldAccess() {
        Class<TestCar> carClass = TestCar.class;
    }

    @Test
    @DisplayName("인자를 가진 생성자의 인스턴스 생성")
    void initClazz() {
        Class<TestCar> carClass = TestCar.class;

    }
}
