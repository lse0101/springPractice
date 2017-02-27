package springbook.learningtest.template;

import org.junit.*;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lse0101 on 03/02/2017.
 */
public class CalcSumTest {

    Calculator calculator;
    String numFlepath;

    @Before
    public void setUp() throws Exception {
        this.calculator = new Calculator();
        this.numFlepath = getClass().getClassLoader().getResource("numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        int sum = this.calculator.calcSum(this.numFlepath);
        assertThat(sum, is(10));
    }

    @Test
    public void multiplyOfNumbers() throws Exception {
        assertThat(calculator.calcMultiply(this.numFlepath), is(24));

    }
}
