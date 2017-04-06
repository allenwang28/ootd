package com.qinglenmeson.ootd;

import java.io.File;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public class Clothing {
    private String name;
    private Category category;
    private Warmth warmth;
    private Occasion occasion;
    private File photo;
    private int color;

    private int timesWorn;
    private Cleanliness cleanliness;

    // Some default constructor

    //If we do factory method, maybe we should remove general constructor so everything is specific
    public Clothing() {
        this.name = "";
        this.category = Category.PANTS;
        this.warmth = Warmth.COLD;
        this.occasion = Occasion.ATHLETIC;
        this.timesWorn = 0;
        this.cleanliness = Cleanliness.CLEAN;
        this.color = -16777216;
    }

    public Clothing(String name, Category category, Warmth warmth, Occasion occasion) {
        this.name = name;
        this.category = category;
        this.warmth = warmth;
        this.occasion = occasion;
        this.timesWorn = 0;
        this.cleanliness = Cleanliness.CLEAN;
        this.color = -16777216;
    }

    public void minusWear() {
        timesWorn--;
        if (timesWorn <= 0) timesWorn = 0;
    }

    public void plusWear() {
        timesWorn++;
    }

    // Getters/Setters
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Warmth getWarmth() {
        return warmth;
    }

    public void setWarmth(Warmth warmth) {
        this.warmth = warmth;
    }

    public Occasion getOccasion() {
        return occasion;
    }

    public void setOccasion(Occasion occasion) {
        this.occasion = occasion;
    }

    public int getTimesWorn() {
        return timesWorn;
    }

    public void setColor(int color) {
        this.color = color;
    }
    public int getColor() {
        return color;
    }

    public void setTimesWorn(int timesWorn) {
        this.timesWorn = timesWorn;
    }

    public Cleanliness getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(Cleanliness cleanliness) {
        this.cleanliness = cleanliness;
    }

    public void setPhoto(File file) {
        this.photo = file;
    }

    //Make this an interface at some point, with a isMatch() function using Jame's algo, implemented by our little subclasses
}
