package com.qinglenmeson.ootd;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public enum Occasion {
    CASUAL(R.string.occasion1),
    FORMAL(R.string.occasion2),
    ATHLETIC(R.string.occasion3),
    SWIM(R.string.occasion4);

    private int resourceId;

    Occasion(int id) {
        resourceId = id;
    }

    @Override
    public String toString() {
        return App.getContext().getString(resourceId);
    }

    public static Occasion fromString(String id) {
        for (Occasion o : values()) {
            if (o.toString().equalsIgnoreCase(id)) {
                return o;
            }
        }
        return null;
    }
}
