package io.github.liquidjoo.simplehiberante.proxy.study.lazy;

import domain.Order;
import domain.OrderItem;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.List;

class DefaultLazyLoadingHandlerTest {

    @Test
    void name() {
        LazyLoadingHandler<List<OrderItem>> loader = new OrderItemsLazyLoader();
        DefaultLazyLoadingHandler handler = new DefaultLazyLoadingHandler(loader);

        List<OrderItem> orderItems1 = (List<OrderItem>) Proxy.newProxyInstance(
                List.class.getClassLoader(),
                new Class<?>[]{List.class},
                handler
        );
        // Order 객체 생성
        Order order = new Order(1L, "a", orderItems1);

        System.out.println("Order created. Accessing orderItems...");
        List<OrderItem> orderItems = order.getOrderItems(); // 첫 접근 시 로딩
        System.out.println("Order Items: " + orderItems);

        System.out.println("Accessing orderItems again...");
        orderItems = order.getOrderItems(); // 이후에는 캐시된 데이터 사용
        System.out.println("Order Items: " + orderItems);
    }
}