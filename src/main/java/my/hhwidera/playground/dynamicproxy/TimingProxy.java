package my.hhwidera.playground.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class TimingProxy implements InvocationHandler {

    private final Object subject;

    TimingProxy(Object subject) {
        this.subject = subject;
    }

    public static <T> T bind(T instance) {
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(
                instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(),
                new TimingProxy(instance)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        try {
            return method.invoke(subject, args);
        } finally {
            long duration = System.nanoTime() - start;
            System.out.println(method.getName() + " took: " + duration + "ns");
        }
    }

}
