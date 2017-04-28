package com.qinglenmeson.ootd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by James Park on 4/25/2017.
 */

public class Outfit {
    private static final int SHIRT_MAX_TIMES_WORN = 3;
    private static final int SWEATER_MAX_TIMES_WORN = 5;
    private static final int PANTS_MAX_TIMES_WORN = 10;
    private static final int JACKET_MAX_TIMES_WORN = 30;
    private static final int SOCKS_MAX_TIMES_WORN = 1;
    private static final int SHOES_MAX_TIMES_WORN = 1000;
    private static final int TSHIRT_MAX_TIMES_WORN = 2;

    private static final Map<Category, Integer> MAX_TIMES_WORN_MAP = createMaxTimesWornMap();
    private static Map<Category, Integer> createMaxTimesWornMap() {
        Map<Category, Integer> map = new HashMap<>();
        map.put(Category.SHIRT, SHIRT_MAX_TIMES_WORN);
        map.put(Category.SWEATER, SWEATER_MAX_TIMES_WORN);
        map.put(Category.PANTS, PANTS_MAX_TIMES_WORN);
        map.put(Category.JACKET, JACKET_MAX_TIMES_WORN);
        map.put(Category.SOCKS, SOCKS_MAX_TIMES_WORN);
        map.put(Category.SHOES, SHOES_MAX_TIMES_WORN);
        map.put(Category.TSHIRT, TSHIRT_MAX_TIMES_WORN);
        return map;
    }

    private Map<Category, Clothing> clothingMap;
    public Outfit() {
        clothingMap = new HashMap<>();
    }


    public Map<Category, Clothing> getClothingMap() {
        return clothingMap;
    }

    public void incrementWear() {
        for (Clothing c : clothingMap.values()) {
            if (c != null) {
                c.plusWear();
            }
        }
    }

    public static Outfit generate(Day day) {
        Outfit outfit = new Outfit();

        if (day.rain == true) {
            outfit.setClothing(Category.SHIRT);
            outfit.setClothing(Category.JACKET);
            outfit.setClothing(Category.PANTS);
            outfit.setClothing(Category.SOCKS);
            outfit.setClothing(Category.SHOES);
        }
        else {//no rain
            outfit.setClothing(Category.TSHIRT);
            outfit.setClothing(Category.PANTS);
            outfit.setClothing(Category.SOCKS);
            outfit.setClothing(Category.SHOES);
            if(day.tempLow < 60){
                outfit.setClothing(Category.JACKET);
            }
        }
        return outfit;
    }

    private int compColor(int color){
        return color+180%360;
    }

    private int randomNum(int min, int max) {
        int random = (int) (Math.random() * max + min);
        return random;
    }

    private void setClothing(Category cat){
        Closet closet = Closet.getInstance();
        List<Clothing> clothingArray = closet.getClothesFromCategory(cat);
        for (Clothing c : clothingArray) {
            if (c.getTimesWorn() < MAX_TIMES_WORN_MAP.get(cat)) {
                clothingMap.put(cat, c);
            }
        }
        // Couldn't find a clean article of clothing
        if (!clothingMap.containsKey(cat) && clothingArray.size() != 0) {
            clothingMap.put(cat, clothingArray.get(0));
        }
    }
}