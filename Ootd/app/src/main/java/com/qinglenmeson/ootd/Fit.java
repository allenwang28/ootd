package com.qinglenmeson.ootd;

/**
 * Created by Qing Wang on 3/23/2017.
 */

public enum Fit {
    TIGHT(R.string.fit1),
    PERFECT(R.string.fit2),
    LOOSE(R.string.fit3),
    LONG(R.string.fit4),
    SHORT(R.string.fit5);


    private int resourceId;

    Fit(int id) {
        resourceId = id;
    }

    @Override
    public String toString() {
        return App.getContext().getString(resourceId);
    }

    public static Fit fromString(String id) {
        for (Fit c : values()) {
            if (c.toString().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }
}
