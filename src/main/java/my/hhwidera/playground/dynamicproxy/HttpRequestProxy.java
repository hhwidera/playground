package my.hhwidera.playground.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.BodyPublishers.noBody;
import static java.net.http.HttpResponse.BodyHandlers.ofString;

class HttpRequestProxy implements InvocationHandler {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Request requestDefinition = method.getAnnotation(Request.class);
        HttpRequest request = HttpRequest.newBuilder()
                .method(requestDefinition.method(), noBody())
                .uri(URI.create(requestDefinition.uri()))
                .build();
        HttpResponse<String> response = httpClient.send(request, ofString());
        //noinspection SuspiciousInvocationHandlerImplementation
        return response.body();
    }

    public static <T> T create(Class<T> definition) {
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(
                definition.getClassLoader(),
                new Class<?>[] { definition },
                new HttpRequestProxy()
        );
    }
}