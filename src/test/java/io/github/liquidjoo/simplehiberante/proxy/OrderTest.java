package io.github.liquidjoo.simplehiberante.proxy;

import org.junit.jupiter.api.Test;

import javax.persistence.OneToMany;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void name() {

        Class<Order> orderClass = Order.class;
        Field[] declaredFields = orderClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(OneToMany.class)) {
                System.out.println(declaredField.getType());
                System.out.println(collectionClass(declaredField.getGenericType()));
            }

        }
    }

    private Class<?> collectionClass(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();

            if (typeArguments != null && typeArguments.length > 0) {
                // 첫 번째 타입 인자를 가져옴 (여기서는 String 타입이 됨)
                Type typeArgument = typeArguments[0];
                try {
                    return Class.forName(typeArgument.getTypeName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new IllegalArgumentException();
    }

}