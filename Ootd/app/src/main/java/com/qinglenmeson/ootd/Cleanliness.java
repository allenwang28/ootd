package com.qinglenmeson.ootd;

import java.io.StringBufferInputStream;

/**
 * Created by Allen Wang on 3/21/2017.
 */
public enum Cleanliness {
    CLEAN,
    DIRTY1,
    DIRTY2,
    DIRTY3;

    @Override
    public String toString() {
        switch(this) {
            case CLEAN:
                return "Clean";
            case DIRTY1:
                return "Smells bad";
            case DIRTY2:
                return "Sweaty";
            case DIRTY3:
                return "Don't wear this";
            default:
                throw new IllegalArgumentException();
        }
    }

}
