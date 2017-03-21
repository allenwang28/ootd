package com.qinglenmeson.ootd;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public enum Category {
    BLOUSE,
    SHIRT,
    SWEATER,
    PANTS;
    // TODO - add more categories

    @Override
    public String toString() {
        switch(this) {
            case BLOUSE:
                return "Blouse";
            case SHIRT:
                return "Shirt";
            case SWEATER:
                return "Sweater";
            case PANTS:
                return "Pants";
            default:
                throw new IllegalArgumentException();
        }
    }
}
