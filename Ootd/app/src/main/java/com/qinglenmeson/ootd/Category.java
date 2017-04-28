package com.qinglenmeson.ootd;

/**
 * Created by Allen Wang on 3/21/2017.
 */

//We won't use this one, instead I'm making subclasses of clothing that essentially replaces this

public enum Category {
    SHIRT(R.string.category1),
    SWEATER(R.string.category2),
    PANTS(R.string.category3),
    JACKET(R.string.category4),
    SOCKS(R.string.category5),
    SHOES(R.string.category6),
    TSHIRT(R.string.category7);

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
