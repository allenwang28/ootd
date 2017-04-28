package com.qinglenmeson.ootd;

import java.util.ArrayList;
import java.math.*;
import java.util.List;

/**
 * Created by James Park on 4/25/2017.
 */

public class Outfit {

    private Clothing shirt;
    private Clothing sweater;
    private Clothing pants;
    private Clothing jacket;
    private Clothing socks;
    private Clothing shoes;
    private Clothing tshirt;

    Category shirtCat = Category.SHIRT;
    Category sweaterCat = Category.SWEATER;
    Category pantsCat = Category.PANTS;
    Category jacketCat = Category.JACKET;
    Category socksCat = Category.SOCKS;
    Category shoesCat = Category.SHOES;
    Category tshirtCat = Category.TSHIRT;

    public Clothing getShirt() {
        return shirt;
    }

    public Clothing getSweater() {
        return sweater;
    }

    public Clothing getPants() {
        return pants;
    }

    public Clothing getJacket() {
        return jacket;
    }

    public Clothing getSocks() {
        return socks;
    }

    public Clothing getShoes() {
        return shoes;
    }

    public Clothing getTshirt(){
        return tshirt;
    }



    public static final int shirtMaxTimesWorn = 3;
    public static final int sweaterMaxTimesWorn = 5;
    public static final int pantsMaxTimesWorn = 10;
    public static final int jacketMaxTimesWorn = 30;
    public static final int socksMaxTimesWorn = 1;
    public static final int shoesMaxTimesWorn = 1000;
    public static final int tshirtMaxTimesWorn = 2;

    private int returnMaxTimesWorn(Category cat){
        if (cat.equals(Category.SHIRT)){
            return shirtMaxTimesWorn;
        }
        if (cat.equals(Category.SWEATER)){
            return sweaterMaxTimesWorn;
        }
        if (cat.equals(Category.PANTS)){
            return pantsMaxTimesWorn;
        }
        if (cat.equals(Category.JACKET)){
            return jacketMaxTimesWorn;
        }
        if (cat.equals(Category.SOCKS)){
            return socksMaxTimesWorn;
        }
        if (cat.equals(Category.SHOES)){
            return shoesMaxTimesWorn;
        }
        if (cat.equals(Category.TSHIRT)){
            return tshirtMaxTimesWorn;
        }
        else
            return 5;
    }

    private Clothing returnClothingFromCat(Category cat){
        if (cat.equals(Category.SHIRT)){
            return shirt;
        }
        if (cat.equals(Category.SWEATER)){
            return sweater;
        }
        if (cat.equals(Category.PANTS)){
            return pants;
        }
        if (cat.equals(Category.JACKET)){
            return jacket;
        }
        if (cat.equals(Category.SOCKS)){
            return socks;
        }
        if (cat.equals(Category.SHOES)){
            return shoes;
        }
        if (cat.equals(Category.TSHIRT)){
            return tshirt;
        }
    }



    public void Outfit() {

    }

    public void increaseDirty(){
        if(shirt != null){shirt.setTimesWorn(shirt.getTimesWorn() + 1);}
        if(sweater != null){sweater.setTimesWorn(sweater.getTimesWorn() + 1);}
        if(pants != null){pants.setTimesWorn(pants.getTimesWorn() + 1);}
        if(jacket != null){jacket.setTimesWorn(shirt.getTimesWorn() + 1);}
        if(socks != null){socks.setTimesWorn(socks.getTimesWorn() + 1);}
        if(shoes != null){shoes.setTimesWorn(shoes.getTimesWorn() + 1);}
        if(tshirt != null){tshirt.setTimesWorn(tshirt.getTimesWorn() + 1);}
    }

    public Outfit generate(Closet closet, Day day) {
        Outfit outfit = new Outfit();

        if (day.rain == true) {
            setClothing(closet, Category.SHIRT);
            setClothing(closet, Category.JACKET);
            setClothing(closet, Category.PANTS);
            setClothing(closet, Category.SOCKS);
            setClothing(closet, Category.SHOES);
        }
        else {//no rain
            setClothing(closet, Category.TSHIRT);
            setClothing(closet, Category.PANTS);
            setClothing(closet, Category.SOCKS);
            setClothing(closet, Category.SHOES);
            if(day.tempLow < 60){
                setClothing(closet, Category.JACKET);
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

    private void setJacket(Closet closet){
        List<Clothing> jacketArray = closet.getClothesFromCategory(jacketCat);
        int numItems = jacketArray.size();
        int randomJacketIndex = randomNum(0, numItems-1);
        this.jacket = jacketArray.get(randomJacketIndex);

        while (jacket.getTimesWorn() > jacketMaxTimesWorn && numItems > 0){
            jacket.setCleanliness(Cleanliness.DIRTY);
            jacketArray.remove(randomJacketIndex);
            numItems -= 1;
            if (numItems == 0){
                break;  //exit while loop, all jackets are dirty, so the last jacket is worn
            }
            randomJacketIndex = randomNum(0, numItems-1);
            jacket = jacketArray.get(randomJacketIndex);
        }
    }

    private void setShirt(Closet closet){
        List<Clothing> shirtArray = closet.getClothesFromCategory(shirtCat);
        int numItems = shirtArray.size();
        int randomShirtIndex = randomNum(0, numItems-1);
        this.shirt = shirtArray.get(randomShirtIndex);

        while (shirt.getTimesWorn() > shirtMaxTimesWorn && numItems > 0){
            shirt.setCleanliness(Cleanliness.DIRTY);
            shirtArray.remove(randomShirtIndex);
            numItems -= 1;
            if (numItems == 0){
                break;  //exit while loop, all shirts are dirty, so the last shirt is worn
            }
            randomShirtIndex = randomNum(0, numItems-1);
            shirt = shirtArray.get(randomShirtIndex);
        }
    }

    private void setClothing(Closet closet, Category cat){
        Clothing item = returnClothingFromCat(cat);
        List<Clothing> itemArray = closet.getClothesFromCategory(cat);
        int numItems = itemArray.size();
        int randomItemIndex = randomNum(0, numItems-1);
        item = itemArray.get(randomItemIndex);

        while (item.getTimesWorn() > returnMaxTimesWorn(cat) && numItems > 0){
            item.setCleanliness(Cleanliness.DIRTY);
            itemArray.remove(randomItemIndex);
            numItems -= 1;
            if (numItems == 0){
                break;  //exit while loop, all clothing of that category are dirty, so the last shirt is worn
            }
            randomItemIndex = randomNum(0, numItems-1);
            item = itemArray.get(randomItemIndex);
        }
    }



}