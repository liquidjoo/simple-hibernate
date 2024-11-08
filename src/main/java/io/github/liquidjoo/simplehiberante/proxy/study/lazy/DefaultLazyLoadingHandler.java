package io.github.liquidjoo.simplehiberante.proxy.study.lazy;

import domain.OrderItem;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class DefaultLazyLoadingHandler implements InvocationHandler {

    private final LazyLoadingHandler<List<OrderItem>> loader;
    private List<OrderItem> cachedItems;
    private boolean isLoaded = false;

    public DefaultLazyLoadingHandler(LazyLoadingHandler<List<OrderItem>> loader) {
        this.loader = loader;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!isLoaded) {
            System.out.println("abcccccc");
            cachedItems = loader.load();
            isLoaded = true;
        }

        return method.invoke(cachedItems, args);
    }
}
