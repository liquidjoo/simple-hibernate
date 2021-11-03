package io.github.liquidjoo.simplehiberante.product;

import io.github.liquidjoo.simplehiberante.annotation.SimpleColumn;
import io.github.liquidjoo.simplehiberante.annotation.SimpleEntity;
import io.github.liquidjoo.simplehiberante.annotation.SimpleId;
import io.github.liquidjoo.simplehiberante.step1.ReflectionApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProductTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionApiTest.class);

    @Test
    @DisplayName("클래스 정보 출력")
    void getClassInfo() {
        Class<Product> clazz = Product.class;
        logger.debug(clazz.getName());

        assertAll("", () -> {
            assertThat(clazz.getSimpleName()).isEqualTo("Product");
            assertThat(clazz.isAnnotationPresent(SimpleEntity.class)).isTrue();
        });
    }

    @Test
    @DisplayName("클래스 필드 정보 출력")
    void getFieldsInfo() {
        Class<Product> clazz = Product.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            logger.debug(declaredField.getName());
        }
    }

    @Test
    @DisplayName("고유 값을 확인")
    void getFieldBySimpleIdAnnotation() {
        Class<Product> clazz = Product.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(SimpleId.class)) {
                logger.debug(declaredField.getName());
            }
        }
    }

    @Test
    @DisplayName("컬럼 값 확인")
    void getFieldBySimpleColumnAnnotation() {
        Class<Product> clazz = Product.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(SimpleColumn.class)) {
                logger.debug(declaredField.getName());
            }
        }
    }
}
