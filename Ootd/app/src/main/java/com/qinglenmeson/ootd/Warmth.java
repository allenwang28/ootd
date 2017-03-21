package com.qinglenmeson.ootd;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public enum Warmth {
    // TODO - find better names
    COLD,
    WARM,
    HOT;

    @Override
    public String toString() {
        switch (this) {
            case COLD:
                return "Cold";
            case WARM:
                return "Warm";
            case HOT:
                return "Hot";
            default:
                throw new IllegalArgumentException();
        }
    }
}
