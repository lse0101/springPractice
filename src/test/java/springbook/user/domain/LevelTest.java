package springbook.user.domain;

import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by lse0101 on 09/02/2017.
 */
public class LevelTest {

    @Test
    public void testIntValue() throws Exception {
        assertThat(Level.BASIC, is(Level.BASIC));
        assertThat(Level.BASIC.intValue(), is(1));
        assertThat(Level.SILVER.intValue(), is(2));
        assertThat(Level.GOLD.intValue(), is(3));
    }

    @Test
    public void testValueOf() throws Exception {
        assertThat(Level.valueOf(1), is(Level.BASIC));
        assertThat(Level.valueOf(2), is(Level.SILVER));
        assertThat(Level.valueOf(3), is(Level.GOLD));
    }
}