package springbook.learningtest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lse0101 on 22/02/2017.
 */
public class UppercaseHandler implements InvocationHandler{
    Hello target;

    public UppercaseHandler(Hello target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = method.invoke(target, args);
        if (ret instanceof String && method.getName().startsWith("say")) {
            return ((String)ret).toUpperCase();
        } else {
            return ret;
        }
    }
}
