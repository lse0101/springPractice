package springbook.learningtest.spring.pointcut;

/**
 * Created by lse0101 on 28/02/2017.
 */
public interface TargetInterface {
    public void hello();
    public void hello(String a);
    public int minus(int a, int b) throws RuntimeException;
    public int plus(int a, int b);
}
