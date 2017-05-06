package com.qinglenmeson.ootd;

import android.content.Context;
import android.graphics.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertEquals;

/**
 * Created by Qing Wang on 4/27/2017.
 */

public class ClosetTest {
    Context context;
    Closet closet;

    private Outfit createOutfit() {
        String weatherInfo = "{\"coord\":{\"lon\":-122.08,\"lat\":37.39},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"base\":\"stations\",\"main\":{\"temp\":277.14,\"pressure\":1025,\"humidity\":86,\"temp_min\":275.15,\"temp_max\":279.15},\"visibility\":16093,\"wind\":{\"speed\":1.67,\"deg\":53.0005},\"clouds\":{\"all\":1},\"dt\":1485788160,\"sys\":{\"type\":1,\"id\":471,\"message\":0.0116,\"country\":\"US\",\"sunrise\":1485789140,\"sunset\":1485826300},\"id\":5375480,\"name\":\"Mountain View\",\"cod\":200}";
        Day d = Day.getInstance(weatherInfo);

        Clothing pants = new Clothing("pantaloons", Category.PANTS, Warmth.HOT, Occasion.CASUAL, Cleanliness.DIRTY, Color.RED, "pantaloons.jpg");
        Clothing shirt = new Clothing("firstshirt", Category.SHIRT, Warmth.COLD, Occasion.FORMAL, Cleanliness.CLEAN, Color.BLUE, "firstshirt.jpg");
        closet.saveClothing(pants);
        closet.saveClothing(shirt);

        Outfit outfit = Outfit.generate();
        return outfit;
    }

    private Boolean clothesAreEqual(Clothing c1, Clothing c2) {
        if (!c1.getName().equals(c2.getName())) {
            return false;
        }
        if (c1.getTimesWorn() != c2.getTimesWorn()) {
            return false;
        }
        if (c1.getCategory() != c2.getCategory()) {
            return false;
        }
        if (c1.getCleanliness() != c2.getCleanliness()) {
            return false;
        }
        if (c1.getOccasion() != c2.getOccasion()) {
            return false;
        }
        if (c1.getWarmth() != c2.getWarmth()) {
            return false;
        }
        if (c1.getColor() != c2.getColor()) {
            return false;
        }
        if (!(c1.getPhoto().equals(c2.getPhoto()))) {
            return false;
        }
        return true;
    }

    private Boolean outfitsAreEqual(Outfit o1, Outfit o2) {
        Map<Category, Clothing> clothingMap1 = o1.getClothingMap();
        Map<Category, Clothing> clothingMap2 = o2.getClothingMap();
        for (Category c : Category.values()) {
            Clothing clothing1, clothing2;
            if (clothingMap1.containsKey(c)) {
                // Clothing exists for 1 but not 2
                if (!clothingMap2.containsKey(c)) { return false; }
                clothing1 = clothingMap1.get(c);
            } else {
                // In this case the clothing exists for outfit 2 but not 1
                if (clothingMap2.containsKey(c)) { return false; }
                // Here this means that neither clothing exists
                continue;
            }
            clothing2 = clothingMap2.get(c);
            if (!clothesAreEqual(clothing1, clothing2)) {
                return false;
            }
        }
        return true;
    }

    private void populateClosetWithClothing() {
        Clothing clothing = new Clothing("pantaloons", Category.PANTS, Warmth.HOT, Occasion.CASUAL, Cleanliness.DIRTY, Color.RED,  "pantaloons.jpg");
        Clothing clothing2 = new Clothing("firstshirt", Category.SHIRT, Warmth.COLD, Occasion.FORMAL, Cleanliness.CLEAN, Color.BLUE,  "firstshirt.jpg");
        Clothing clothing3 = new Clothing("sweat", Category.SWEATER, Warmth.WARM, Occasion.FORMAL, Cleanliness.DIRTY, Color.CYAN,  "sweat.jpg");
        Clothing clothing4 = new Clothing("la pants", Category.PANTS, Warmth.HOT, Occasion.SWIM);

        closet.saveClothing(clothing);
        closet.saveClothing(clothing2);
        closet.saveClothing(clothing3);
        closet.saveClothing(clothing4);
    }

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
        assertEquals(0, closet.clothingListSize());
    }

    @Test
    public void addSingleClothing() {
        Clothing clothing = new Clothing("pantaloons", Category.PANTS, Warmth.HOT, Occasion.CASUAL, Cleanliness.DIRTY, Color.RED,  "pantaloons.jpg");

        closet.saveClothing(clothing);
        List<Clothing> list = closet.getClothingList();
        assertTrue(list.size() == 1);

        Clothing _clothing = list.get(0);

        assertEquals("pantaloons", _clothing.getName());
        assertEquals(Category.PANTS, _clothing.getCategory());
        assertEquals(Warmth.HOT, _clothing.getWarmth());
        assertEquals(Occasion.CASUAL, _clothing.getOccasion());
        assertEquals(Cleanliness.DIRTY, _clothing.getCleanliness());
        assertEquals(Color.RED, _clothing.getColor());
        assertEquals("pantaloons.jpg", _clothing.getPhoto());
        assertEquals(0, _clothing.getTimesWorn());
    }

    @Test
    public void addMultClothing() {
        Clothing clothing = new Clothing("pantaloons", Category.PANTS, Warmth.HOT, Occasion.CASUAL, Cleanliness.DIRTY, Color.RED,  "pantaloons.jpg");
        Clothing clothing2 = new Clothing("firstshirt", Category.SHIRT, Warmth.COLD, Occasion.FORMAL, Cleanliness.CLEAN, Color.BLUE,  "firstshirt.jpg");
        Clothing clothing3 = new Clothing("sweat", Category.SWEATER, Warmth.WARM, Occasion.FORMAL, Cleanliness.DIRTY, Color.CYAN,  "sweat.jpg");
        Clothing clothing4 = new Clothing("la pants", Category.PANTS, Warmth.HOT, Occasion.SWIM);

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
        assertEquals(Cleanliness.DIRTY, _clothing.getCleanliness());
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
        assertEquals(Cleanliness.DIRTY, _clothing3.getCleanliness());
        assertEquals(Color.CYAN, _clothing3.getColor());
        assertEquals("sweat.jpg", _clothing3.getPhoto());
        assertEquals(0, _clothing3.getTimesWorn());

        assertEquals("la pants", _clothing4.getName());
        assertEquals(Category.PANTS, _clothing4.getCategory());
        assertEquals(Warmth.HOT, _clothing4.getWarmth());
        assertEquals(Occasion.SWIM, _clothing4.getOccasion());
        assertEquals(Color.BLACK, _clothing4.getColor());
        assertEquals(null, _clothing4.getPhoto());
        assertEquals(Cleanliness.CLEAN, _clothing4.getCleanliness());
        assertEquals(0, _clothing4.getTimesWorn());
    }

    @Test
    public void removeClothing() {
        Clothing clothing = new Clothing("pantaloons", Category.PANTS, Warmth.HOT, Occasion.CASUAL, Cleanliness.DIRTY, Color.RED, "pantaloons.jpg");
        Clothing clothing2 = new Clothing("firstshirt", Category.SHIRT, Warmth.COLD, Occasion.FORMAL, Cleanliness.CLEAN, Color.BLUE, "firstshirt.jpg");
        Clothing clothing3 = new Clothing("sweat", Category.SWEATER, Warmth.WARM, Occasion.FORMAL, Cleanliness.DIRTY, Color.CYAN, "sweat.jpg");
        Clothing clothing4 = new Clothing("la pants", Category.PANTS, Warmth.HOT, Occasion.SWIM);

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

    @Test
    public void massiveClothingNumberTest() {
        int LARGE_NUM = 10000;
        Clothing c = new Clothing("pantaloons", Category.PANTS, Warmth.HOT, Occasion.CASUAL, Cleanliness.DIRTY, Color.RED, "pantaloons.jpg");
        for (int i = 0; i < LARGE_NUM; ++i) {
            closet.saveClothing(new Clothing("pantaloons", Category.PANTS, Warmth.HOT, Occasion.CASUAL, Cleanliness.DIRTY, Color.RED, "pantaloons.jpg"));
        }
        assertEquals(closet.clothingListSize(), LARGE_NUM);
        for (int i = 0; i < LARGE_NUM; ++i) {
            assertTrue(clothesAreEqual(c, closet.getClothingList().get(i)));
        }
    }

    @Test
    public void addSingleOutfitTest() {
        Outfit outfit = createOutfit();
        closet.saveOutfit(outfit);
        assertEquals(closet.outfitListSize(), 1);
        assertTrue(outfitsAreEqual(outfit, closet.getOutfitList().get(0)));
    }

    @Test
    public void massiveOutfitNumberTest() {
        int LARGE_NUM = 1000;
        // should always create the same outfit since there's only 2 clothes elements
        Outfit o = createOutfit();
        for (int i = 0; i < LARGE_NUM; ++i) {
            closet.saveOutfit(createOutfit());
        }
        assertEquals(closet.outfitListSize(), LARGE_NUM);
        for (int i = 0; i < LARGE_NUM; ++i) {
            assertTrue(outfitsAreEqual(o, closet.getOutfitList().get(i)));
        }
    }

    @Test
    public void todaysOutfitTest1() {
        Outfit o = createOutfit();

        for (Clothing c : o.getClothingMap().values()) {
            assertEquals(c.getTimesWorn(), 0);
        }

        closet.saveTodaysOutfit(o);

        for (Clothing c : o.getClothingMap().values()) {
            assertEquals(c.getTimesWorn(), 1);
        }

        closet.clearTodaysOutfit();

        for (Clothing c : o.getClothingMap().values()) {
            assertEquals(c.getTimesWorn(), 0);
        }
   }

    @Test
    public void todaysOutfitTest2() {
        Outfit o1 = createOutfit();
        Outfit o2 = createOutfit();

        for (Clothing c : o1.getClothingMap().values()) {
            assertEquals(c.getTimesWorn(), 0);
        }
        for (Clothing c : o2.getClothingMap().values()) {
            assertEquals(c.getTimesWorn(), 0);
        }

        closet.saveTodaysOutfit(o1);

        for (Clothing c : o1.getClothingMap().values()) {
            assertEquals(c.getTimesWorn(), 1);
        }
        for (Clothing c : o2.getClothingMap().values()) {
            assertEquals(c.getTimesWorn(), 0);
        }

        closet.saveTodaysOutfit(o2);
        for (Clothing c : o1.getClothingMap().values()) {
            assertEquals(c.getTimesWorn(), 0);
        }
        for (Clothing c : o2.getClothingMap().values()) {
            assertEquals(c.getTimesWorn(), 1);
        }

        closet.clearTodaysOutfit();

        for (Clothing c : o1.getClothingMap().values()) {
            assertEquals(c.getTimesWorn(), 0);
        }
        for (Clothing c : o2.getClothingMap().values()) {
            assertEquals(c.getTimesWorn(), 0);
        }
    }

    @Test
    public void testMultipleUsers() {
        String defaultUserName = "";
        String userName1 = "1";

        closet.setOwner(defaultUserName);
        closet.reset();

        populateClosetWithClothing();
        assertEquals(closet.clothingListSize(), 4);

        closet.setOwner(userName1);
        assertEquals(closet.clothingListSize(), 0);

        closet.setOwner(defaultUserName);
        assertEquals(closet.clothingListSize(), 4);
    }


}
