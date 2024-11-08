package io.github.liquidjoo.simplehiberante.proxy.study.lazy4;

import domain.Order;
import domain.OrderItem;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.List;

class LazyLoadingListHandlerTest {

    @Test
    void name() {

        Order order = new Order(1L, "first", null);

        List<OrderItem> orderItems = (List<OrderItem>) Proxy.newProxyInstance(
                List.class.getClassLoader(),
                new Class[]{List.class},
                new LazyLoadingListHandler<>(OrderItem.class)
        );

        order.setOrderItems(orderItems);

        for (OrderItem orderItem : order.getOrderItems()) {
            System.out.println(orderItem.getId());
            System.out.println(orderItem.getProduct());
        }
    }
}