package com.qinglenmeson.ootd;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Allen Wang on 4/25/2017.
 */

public class ClothingTest {
    @Test
    public void emptyInitializationTest() throws Exception {
        Clothing clothing = new Clothing();
        assertTrue(clothing.getName().equals(""));
        assertTrue(clothing.getTimesWorn() == 0);
        assertTrue(clothing.getCleanliness() == Cleanliness.CLEAN);
    }

    @Test
    public void testIncrementDecrement() throws Exception {
        Clothing clothing = new Clothing();
        assertEquals(0, clothing.getTimesWorn());
        clothing.plusWear();
        assertEquals(1, clothing.getTimesWorn());
        clothing.plusWear();
        assertEquals(2, clothing.getTimesWorn());
        clothing.plusWear();
        assertEquals(3, clothing.getTimesWorn());
        clothing.minusWear();
        assertEquals(2, clothing.getTimesWorn());
        clothing.minusWear();
        assertEquals(1, clothing.getTimesWorn());
        clothing.minusWear();
        assertEquals(0, clothing.getTimesWorn());
        clothing.minusWear();
        assertEquals(0, clothing.getTimesWorn());
    }
}
