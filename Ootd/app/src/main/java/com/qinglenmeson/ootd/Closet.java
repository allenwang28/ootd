package com.qinglenmeson.ootd;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen Wang on 4/25/2017.
 *
 * A closet implemented with singleton
 */
public class Closet {
    private static Closet mInstance = null;

    private List<Clothing> mClothingList;
    private Map<Category, List<Clothing>> mClothingMap;

    private Closet() {
        System.out.println("Closet Initializing");
    }

    private void addClothing(Clothing clothing) {
        mClothingList.add(clothing);
        mClothingMap.get(clothing.getCategory()).add(clothing);
    }

    private void loadClothesFromMemory() {
        mClothingList = new ArrayList<>();
        mClothingMap = new HashMap<>();

        for (Category c : Category.values()) {
            mClothingMap.put(c, new ArrayList<Clothing>());
        }

        String filename = "CLOSET.txt";
        StringBuffer sbuff = new StringBuffer("");
        String[] tempSplit;

        try {
            FileInputStream fin = App.getContext().openFileInput(filename);
            InputStreamReader isr = new InputStreamReader ( fin ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
                sbuff.append(readString);
                readString = buffreader.readLine ( ) ;
            }
            fin.close();
            isr.close () ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        tempSplit = sbuff.toString().trim().split("/split/");

        int words = 0;


        while(words+6 < tempSplit.length) {
            String name = tempSplit[words];
            String category = tempSplit[words+1];
            String warmth = tempSplit[words+2];
            String occasion = tempSplit[words+3];
            String cleanliness = tempSplit[words+4];
            String color = tempSplit[words+5];
            String photo = tempSplit[words+6];
            words = words + 7;

            System.out.println("Name: " + name);
            System.out.println("Category: " + category);
            System.out.println("Warmth: " + warmth);
            System.out.println("Occasion: " + occasion);
            System.out.println("Cleanliness: " + cleanliness);
            System.out.println("Color: " + color);
            System.out.println(photo);

            addClothing(new Clothing(name,
                    Category.fromString(category),
                    Warmth.fromString(warmth),
                    Occasion.fromString(occasion),
                    Cleanliness.fromString(cleanliness),
                    Integer.parseInt(color),
                    photo));

        }
    }

    private void resetMemory() {
        String filename = "CLOSET.txt";
        try {
            FileOutputStream fos = App.getContext().openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();
        } catch(Exception e) {
            Log.e("ClothingActivity", e.toString());
        }
    }

    private void saveClothingToMemory(Clothing clothing) {
        String filename = "CLOSET.txt";
        String string = clothing.getName() + "/split/" + clothing.getCategory().toString() + "/split/"
                + clothing.getWarmth().toString() + "/split/" + clothing.getOccasion().toString() + "/split/"
                + clothing.getCleanliness().toString() + "/split/" + clothing.getColor() + "/split/"
                + clothing.getPhoto() + "/split/";
        try {
            FileOutputStream fos = App.getContext().openFileOutput(filename, Context.MODE_APPEND);
            fos.write(string.getBytes());
            fos.close();
        } catch(Exception e) {
            Log.e("ClothingActivity", e.toString());
        }
    }

    // Saves clothing to internal storage but also adds to our local lists
    public void saveClothing(Clothing clothing) {
        addClothing(clothing);
        saveClothingToMemory(clothing);
    }

    public List<Clothing> getClothesFromCategory(Category c) {
        return mClothingMap.get(c);
    }

    public static Closet getInstance() {
        if (mInstance == null) {
            mInstance = new Closet();
            mInstance.loadClothesFromMemory();
        }
        return mInstance;
    }

    public void removeClothing(Clothing c) {
        if (!mClothingList.contains(c)) {
            return;
        }
        if (!mClothingMap.get(c.getCategory()).contains(c)) {
            return;
        }
        mClothingList.remove(c);
        mClothingMap.get(c.getCategory()).remove(c);
        resetMemory();
        for (Clothing mC : mClothingList) {
            saveClothingToMemory(mC);
        }
    }

    public void reset() {
        resetMemory();
        mClothingList = new ArrayList<>();
        mClothingMap = new HashMap<>();
        for (Category c : Category.values()) {
            mClothingMap.put(c, new ArrayList<Clothing>());
        }
    }

    public List<Clothing> getClothingList() {
        return mClothingList;
    }

    //public void setClothingList(List<Clothing> clothingList) {
    //    mClothingList = clothingList;
    //}

    public int size() {
        return mClothingList.size();
    }
}
