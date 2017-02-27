package springbook.learningtest.template;

/**
 * Created by lse0101 on 03/02/2017.
 */
public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
