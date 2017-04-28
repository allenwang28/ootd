package com.qinglenmeson.ootd;

import android.graphics.Color;
import android.os.Environment;

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

    //Tests the other constructors for clothes with multiple values as well
    // as makes sure the different Objects for Clothing characteristics work well
    @Test
    public void sampleClothingTest() throws Exception {
        Clothing clothing = new Clothing("pantaloons", Category.PANTS, Warmth.HOT, Occasion.CASUAL, Cleanliness.DIRTY, Color.RED,  "pantaloons.jpg");
        assertEquals("pantaloons", clothing.getName());
        assertEquals(Category.PANTS, clothing.getCategory());
        assertEquals(Warmth.HOT, clothing.getWarmth());
        assertEquals(Occasion.CASUAL, clothing.getOccasion());
        assertEquals(Cleanliness.DIRTY, clothing.getCleanliness());
        assertEquals(Color.RED, clothing.getColor());
        assertEquals("pantaloons.jpg", clothing.getPhoto());
        assertEquals(0, clothing.getTimesWorn());

        Clothing clothing2 = new Clothing("firstshirt", Category.SHIRT, Warmth.COLD, Occasion.FORMAL, Cleanliness.CLEAN, Color.BLUE,  "firstshirt.jpg");
        assertEquals("firstshirt", clothing2.getName());
        assertEquals(Category.SHIRT, clothing2.getCategory());
        assertEquals(Warmth.COLD, clothing2.getWarmth());
        assertEquals(Occasion.FORMAL, clothing2.getOccasion());
        assertEquals(Cleanliness.CLEAN, clothing2.getCleanliness());
        assertEquals(Color.BLUE, clothing2.getColor());
        assertEquals("firstshirt.jpg", clothing2.getPhoto());
        assertEquals(0, clothing2.getTimesWorn());

        Clothing clothing3 = new Clothing("sweat", Category.SWEATER, Warmth.WARM, Occasion.FORMAL, Cleanliness.DIRTY, Color.CYAN,  "sweat.jpg");
        assertEquals("sweat", clothing3.getName());
        assertEquals(Category.SWEATER, clothing3.getCategory());
        assertEquals(Warmth.WARM, clothing3.getWarmth());
        assertEquals(Occasion.FORMAL, clothing3.getOccasion());
        assertEquals(Cleanliness.DIRTY, clothing3.getCleanliness());
        assertEquals(Color.CYAN, clothing3.getColor());
        assertEquals("sweat.jpg", clothing3.getPhoto());
        assertEquals(0, clothing3.getTimesWorn());

        Clothing clothing4 = new Clothing("la pants", Category.PANTS, Warmth.HOT, Occasion.SWIM);
        assertEquals("la pants", clothing4.getName());
        assertEquals(Category.PANTS, clothing4.getCategory());
        assertEquals(Warmth.HOT, clothing4.getWarmth());
        assertEquals(Occasion.SWIM, clothing4.getOccasion());
        //Defaults
        assertEquals(Color.BLACK, clothing4.getColor());
        assertEquals(null, clothing4.getPhoto());
        assertEquals(Cleanliness.CLEAN, clothing4.getCleanliness());
        assertEquals(0, clothing4.getTimesWorn());
    }
}
