package io.github.liquidjoo.simplehiberante.proxy.study.lazy4;

import domain.OrderItem;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class LazyLoadingListHandler<T> implements InvocationHandler {
    private final Class<T> targetClass;
    private List<T> target;
    private boolean initialized = false;

    public LazyLoadingListHandler(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!initialized) {

            // db connect
            target = (List<T>) List.of(
                    new OrderItem(1L, "abc", 1)
            );
            initialized = true;
        }
        return method.invoke(target, args);
    }
}
