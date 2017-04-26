package com.qinglenmeson.ootd;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen Wang on 4/25/2017.
 *
 * A closet implemented with singleton
 */
@SuppressWarnings("serial")
public class Closet {
    private static Closet mInstance = null;

    private List<Clothing> mClothingList;

    private Closet() {
        Log.d("Closet", "Initializing");
    }

    public static Closet getInstance() {
        if (mInstance == null) {
            mInstance = new Closet();
        }
        return mInstance;
    }

    public List<Clothing> getClothingList() {
        return mClothingList;
    }

    public void setClothingList(List<Clothing> clothingList) {
        mClothingList = clothingList;
    }
}
