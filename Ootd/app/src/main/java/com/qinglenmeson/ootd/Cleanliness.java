package com.qinglenmeson.ootd;


/**
 * Created by Allen Wang on 3/21/2017.
 */
public enum Cleanliness {
    CLEAN(R.string.cleanliness1),
    DIRTY1(R.string.cleanliness2),
    DIRTY2(R.string.cleanliness3),
    DIRTY3(R.string.cleanliness4);

    private int resourceId;
    private Cleanliness(int id) {
        resourceId = id;
    }

    public int getResourceId() {
        return resourceId;
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
