package io.github.liquidjoo.simplehiberante.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloLazyLoadingHandler implements InvocationHandler {
    private Hello hello;

    public HelloLazyLoadingHandler() {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (hello == null) {
            hello = new HelloTarget();
        }
        return method.invoke(hello, args);
    }
}
