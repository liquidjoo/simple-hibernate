package io.github.liquidjoo.simplehiberante.proxy.study.lazy;

import domain.OrderItem;

import java.util.Arrays;
import java.util.List;

public class OrderItemsLazyLoader implements LazyLoadingHandler<List<OrderItem>> {

    @Override
    public List<OrderItem> load() {
        System.out.println("Loading order items from database...");
        return Arrays.asList(
                new OrderItem(1L, "Product1", 2),
                new OrderItem(2L, "Product2", 3)
        );
    }

}
