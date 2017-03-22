package com.qinglenmeson.ootd;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public enum Warmth {
    COLD(R.string.warmth1),
    WARM(R.string.warmth2),
    HOT(R.string.warmth3);

    private int resourceId;

    Warmth(int id) {
        resourceId = id;
    }

    @Override
    public String toString() {
        return App.getContext().getString(resourceId);
    }

    public static Warmth fromString(String id) {
        for (Warmth w : values()) {
            if (w.toString().equalsIgnoreCase(id)) {
                return w;
            }
        }
        return null;
    }
}
