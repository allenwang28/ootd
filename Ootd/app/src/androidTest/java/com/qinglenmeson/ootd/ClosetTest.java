package com.qinglenmeson.ootd;

import android.content.Context;
import android.graphics.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertEquals;

/**
 * Created by Qing Wang on 4/27/2017.
 */

public class ClosetTest {
    Context context;
    Closet closet;

    @Before
    public void setUp() throws Exception {
        closet = Closet.getInstance();
        closet.reset();
    }

    @After
    public void tearDown() throws Exception {
        closet.reset();
    }

    @Test
    public void multGetInstance() {
        closet = Closet.getInstance();
        Closet closet2 = Closet.getInstance();

        assertEquals(closet, closet2);
    }

    @Test
    public void emptyTest() {
        assertEquals(0, closet.size());
    }

    @Test
    public void addSingleClothing() {
        Clothing clothing = new Clothing("pantaloons", Category.PANTS, Warmth.HOT, Occasion.CASUAL, Cleanliness.DIRTY1, Color.RED,  "pantaloons.jpg");

        closet.saveClothing(clothing);
        List<Clothing> list = closet.getClothingList();
        assertTrue(list.size() == 1);

        Clothing _clothing = list.get(0);

        assertEquals("pantaloons", _clothing.getName());
        assertEquals(Category.PANTS, _clothing.getCategory());
        assertEquals(Warmth.HOT, _clothing.getWarmth());
        assertEquals(Occasion.CASUAL, _clothing.getOccasion());
        assertEquals(Cleanliness.DIRTY1, _clothing.getCleanliness());
        assertEquals(Color.RED, _clothing.getColor());
        assertEquals("pantaloons.jpg", _clothing.getPhoto());
        assertEquals(0, _clothing.getTimesWorn());
    }

    @Test
    public void addMultClothing() {
        Clothing clothing = new Clothing("pantaloons", Category.PANTS, Warmth.HOT, Occasion.CASUAL, Cleanliness.DIRTY1, Color.RED,  "pantaloons.jpg");
        Clothing clothing2 = new Clothing("firstshirt", Category.SHIRT, Warmth.COLD, Occasion.FORMAL, Cleanliness.CLEAN, Color.BLUE,  "firstshirt.jpg");
        Clothing clothing3 = new Clothing("sweat", Category.SWEATER, Warmth.WARM, Occasion.FORMAL, Cleanliness.DIRTY3, Color.CYAN,  "sweat.jpg");
        Clothing clothing4 = new Clothing("la blouse", Category.BLOUSE, Warmth.HOT, Occasion.SWIM);

        closet.saveClothing(clothing);
        closet.saveClothing(clothing2);
        closet.saveClothing(clothing3);
        closet.saveClothing(clothing4);
        List<Clothing> list = closet.getClothingList();
        assertTrue(list.size() == 4);

        Clothing _clothing = list.get(0);
        Clothing _clothing2 = list.get(1);
        Clothing _clothing3 = list.get(2);
        Clothing _clothing4 = list.get(3);

        assertEquals("pantaloons", _clothing.getName());
        assertEquals(Category.PANTS, _clothing.getCategory());
        assertEquals(Warmth.HOT, _clothing.getWarmth());
        assertEquals(Occasion.CASUAL, _clothing.getOccasion());
        assertEquals(Cleanliness.DIRTY1, _clothing.getCleanliness());
        assertEquals(Color.RED, _clothing.getColor());
        assertEquals("pantaloons.jpg", _clothing.getPhoto());
        assertEquals(0, _clothing.getTimesWorn());

        assertEquals("firstshirt", _clothing2.getName());
        assertEquals(Category.SHIRT, _clothing2.getCategory());
        assertEquals(Warmth.COLD, _clothing2.getWarmth());
        assertEquals(Occasion.FORMAL, _clothing2.getOccasion());
        assertEquals(Cleanliness.CLEAN, _clothing2.getCleanliness());
        assertEquals(Color.BLUE, _clothing2.getColor());
        assertEquals("firstshirt.jpg", _clothing2.getPhoto());
        assertEquals(0, _clothing2.getTimesWorn());

        assertEquals("sweat", _clothing3.getName());
        assertEquals(Category.SWEATER, _clothing3.getCategory());
        assertEquals(Warmth.WARM, _clothing3.getWarmth());
        assertEquals(Occasion.FORMAL, _clothing3.getOccasion());
        assertEquals(Cleanliness.DIRTY3, _clothing3.getCleanliness());
        assertEquals(Color.CYAN, _clothing3.getColor());
        assertEquals("sweat.jpg", _clothing3.getPhoto());
        assertEquals(0, _clothing3.getTimesWorn());

        assertEquals("la blouse", _clothing4.getName());
        assertEquals(Category.BLOUSE, _clothing4.getCategory());
        assertEquals(Warmth.HOT, _clothing4.getWarmth());
        assertEquals(Occasion.SWIM, _clothing4.getOccasion());
        assertEquals(Color.BLACK, _clothing4.getColor());
        assertEquals(null, _clothing4.getPhoto());
        assertEquals(Cleanliness.CLEAN, _clothing4.getCleanliness());
        assertEquals(0, _clothing4.getTimesWorn());
    }

    @Test
    public void removeClothing() {
        Clothing clothing = new Clothing("pantaloons", Category.PANTS, Warmth.HOT, Occasion.CASUAL, Cleanliness.DIRTY1, Color.RED, "pantaloons.jpg");
        Clothing clothing2 = new Clothing("firstshirt", Category.SHIRT, Warmth.COLD, Occasion.FORMAL, Cleanliness.CLEAN, Color.BLUE, "firstshirt.jpg");
        Clothing clothing3 = new Clothing("sweat", Category.SWEATER, Warmth.WARM, Occasion.FORMAL, Cleanliness.DIRTY3, Color.CYAN, "sweat.jpg");
        Clothing clothing4 = new Clothing("la blouse", Category.BLOUSE, Warmth.HOT, Occasion.SWIM);

        closet.saveClothing(clothing);
        closet.saveClothing(clothing2);
        closet.saveClothing(clothing3);
        closet.saveClothing(clothing4);
        List<Clothing> list = closet.getClothingList();
        assertTrue(list.size() == 4);

        closet.removeClothing(clothing);
        assertTrue(list.size() == 3);

        closet.removeClothing(clothing2);
        assertTrue(list.size() == 2);

        closet.removeClothing(clothing3);
        assertTrue(list.size() == 1);

        closet.removeClothing(clothing4);
        assertTrue(list.size() == 0);
    }
    }
