package com.qinglenmeson.ootd;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public enum Occasion {
    CASUAL,
    FORMAL,
    ATHLETIC,
    SWIM;

    @Override
    public String toString() {
        switch(this) {
            case CASUAL:
                return "Casual";
            case FORMAL:
                return "Formal";
            case ATHLETIC:
                return "Athletic";
            case SWIM:
                return "Swim";
            default:
                throw new IllegalArgumentException();
        }
    }
}
