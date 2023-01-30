package dev.miikat.farm;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FarmTest {
    private Farm farm;

    @Before
    public void setUp() {
        farm = new Farm("test");
    }

    @After
    public void tearDown() {
        farm = null;
    }

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}
