package com.qinglenmeson.ootd;

/**
 * Created by Qing Wang on 3/23/2017.
 */

public class Shirt extends Clothing {
    private String size;
    private Fit fit;

    public Shirt() {
        super();
        this.size = "Medium";
        this.fit = Fit.PERFECT;
    }

    public Shirt(String name, Category category, Warmth warmth, Occasion occasion, String size, Fit fit) {
        super(name, category, warmth, occasion);
        this.size = size;
        this.fit = fit;
    }

    //Realized function from abstract class Clothing
    public boolean isMatch() {
        //TODO: Implement this using characteristics and outfit algorithm
        return true;
    }

}
