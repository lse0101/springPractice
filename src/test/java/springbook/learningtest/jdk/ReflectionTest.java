package springbook.learningtest.jdk;

import org.junit.*;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lse0101 on 22/02/2017.
 */
public class ReflectionTest {
    @Test
    public void invokeMethod() throws Exception {
        String name = "Spring";

        assertThat(name.length(), is(6));

        Method lengthMethod = String.class.getMethod("length");
        assertThat((Integer)lengthMethod.invoke(name), is(6));

        assertThat(name.charAt(0), is('S'));

        Method chartAtMethod = String.class.getMethod("charAt", int.class);
        assertThat((Character)chartAtMethod.invoke(name, 0), is('S'));

    }
}
