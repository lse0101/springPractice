package springbook.learningtest.proxy;

/**
 * Created by lse0101 on 22/02/2017.
 */
public class HelloToby implements Hello {
    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }

    @Override
    public String sayHi(String name) {
        return "Hi "+name;
    }

    @Override
    public String sayThankYou(String name) {
        return "Thank You "+name;
    }
}
