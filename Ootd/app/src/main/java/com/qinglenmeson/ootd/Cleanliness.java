package com.qinglenmeson.ootd;


/**
 * Created by Allen Wang on 3/21/2017.
 */
public enum Cleanliness {
    CLEAN(R.string.cleanliness1),
    DIRTY(R.string.cleanliness2);


    private int resourceId;

    Cleanliness(int id) {
        resourceId = id;
    }

    @Override
    public String toString() {
        return App.getContext().getString(resourceId);
    }

    public static Cleanliness fromString(String id) {
        for (Cleanliness c : values()) {
            if (c.toString().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }
}
