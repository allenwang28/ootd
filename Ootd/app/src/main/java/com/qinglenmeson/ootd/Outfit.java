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
    private String name;

    public Outfit() {
        clothingMap = new HashMap<>();
    }

    public Outfit(Map<Category, Clothing> clothingMap) {
        this.clothingMap = clothingMap;
    }

    public Map<Category, Clothing> getClothingMap() {
        return clothingMap;
    }

    public String getName()
    {
        if (this.name != null) {
            return this.name;
        } else {
            return "";
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClothingMap(Map<Category, Clothing> clothingMap) {
        this.clothingMap = clothingMap;
    }

    public void decrementWear() {
        for (Clothing c : clothingMap.values()) {
            if (c != null) {
                c.minusWear();
            }
        }
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
        int tempLow = day.getTempLow();
        int tempHigh = day.getTempHigh();
        int tempDif = tempHigh - tempLow;

        int defaultTolerance = 2000000;
        int tolerance = defaultTolerance;

        if (day.rain == true) {
            outfit.setClothing(Category.JACKET);
            outfit.setClothing(Category.SOCKS);
            outfit.setClothing(Category.SHOES);
            outfit.setClothing(Category.PANTS);
            outfit.setClothing(Category.TSHIRT);

            tolerance = defaultTolerance;
            // TODO - check that these actually exist first
            while (!doesItMatch(outfit.clothingMap.get(Category.PANTS).getColor(), outfit.clothingMap.get(Category.TSHIRT).getColor(),tolerance)){
                outfit.setClothing(Category.TSHIRT);
                tolerance += 500000;
            }

            if (tempLow < 50){
                outfit.setClothing(Category.SHIRT);
            }
        }
        else {//no rain
            outfit.setClothing(Category.SOCKS);
            outfit.setClothing(Category.SHOES);
            outfit.setClothing(Category.PANTS);
            outfit.setClothing(Category.TSHIRT);

            tolerance = defaultTolerance;
            while (!doesItMatch(outfit.clothingMap.get(Category.PANTS).getColor(), outfit.clothingMap.get(Category.TSHIRT).getColor(),tolerance)){
                outfit.setClothing(Category.TSHIRT);
                tolerance += 500000;
            }

            if(tempLow < 60){
                outfit.setClothing(Category.JACKET);
            }
            if(tempLow < 50){
                outfit.setClothing(Category.SHIRT);
            }
        }
        return outfit;
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

    private int compColor (int color){
        return (0xFFFFFF - color);
    }

    private static boolean doesItMatch (int color1, int color2, int tolerance){
        if(Math.abs(color2 - color1) < tolerance){
            return true;
        }
        else{
            return false;
        }
    }

    private int hex2Decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

    // precondition:  d is a nonnegative integer
    private String decimal2Hex(int d) {
        String digits = "0123456789ABCDEF";
        if (d == 0) return "0";
        String hex = "";
        while (d > 0) {
            int digit = d % 16;                // rightmost digit
            hex = digits.charAt(digit) + hex;  // string concatenation
            d = d / 16;
        }
        return hex;
    }



}