package io.github.liquidjoo.simplehiberante.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.persistence.EntityManager;

public class LazyLoadingProxyHandler implements InvocationHandler {
    private List<OrderItem> realOrderItems;
    private Long orderId;
    private EntityManager entityManager;

    public LazyLoadingProxyHandler(Long orderId, EntityManager entityManager) {
        this.orderId = orderId;
        this.entityManager = entityManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (realOrderItems == null) {
            System.out.println("Loading OrderItems lazily...");
            realOrderItems = loadOrderItems();
        }
        return method.invoke(realOrderItems, args);
    }

    private List<OrderItem> loadOrderItems() {
        // 실제 데이터베이스에서 OrderItem을 가져오는 로직
        return entityManager.createQuery(
                        "SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId", OrderItem.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public static List<OrderItem> createProxy(Long orderId, EntityManager entityManager) {
        return (List<OrderItem>) Proxy.newProxyInstance(
                List.class.getClassLoader(),
                new Class[]{List.class},
                new LazyLoadingProxyHandler(orderId, entityManager)
        );
    }
}

