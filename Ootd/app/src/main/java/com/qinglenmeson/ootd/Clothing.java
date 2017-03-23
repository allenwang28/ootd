package com.qinglenmeson.ootd;


/**
 * Created by Allen Wang on 3/21/2017.
 */

public class Clothing {
    private String name;
    private Category category;
    private Warmth warmth;
    private Occasion occasion;

    private int timesWorn;
    private Cleanliness cleanliness;

    // TODO - need associated color and image

    // Some default constructor
    public Clothing() {
        this.name = "";
        this.category = Category.PANTS;
        this.warmth = Warmth.COLD;
        this.occasion = Occasion.ATHLETIC;
        this.timesWorn = 0;
        this.cleanliness = Cleanliness.CLEAN;
    }

    public Clothing(String name, Category category, Warmth warmth, Occasion occasion) {
        this.name = name;
        this.category = category;
        this.warmth = warmth;
        this.occasion = occasion;
        this.timesWorn = 0;
        this.cleanliness = Cleanliness.CLEAN;
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

    public void setTimesWorn(int timesWorn) {
        this.timesWorn = timesWorn;
    }

    public Cleanliness getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(Cleanliness cleanliness) {
        this.cleanliness = cleanliness;
    }
}
