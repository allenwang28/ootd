package com.qinglenmeson.ootd;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public enum Category {
    BLOUSE(R.string.category1),
    SHIRT(R.string.category2),
    SWEATER(R.string.category3),
    PANTS(R.string.category4);
    // TODO - add more categories and update strings.xml

    private int resourceId;

    Category(int id) {
        resourceId = id;
    }

    @Override
    public String toString() {
        return App.getContext().getString(resourceId);
    }

    public static Category fromString(String id) {
        for (Category c : values()) {
            if (c.toString().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }
}
