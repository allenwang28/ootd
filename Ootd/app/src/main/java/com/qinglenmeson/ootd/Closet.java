package com.qinglenmeson.ootd;

import android.content.Context;
import android.util.Log;
import android.widget.PopupMenu;

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

    private final String CLOTHES_LIST_BASE = "CLOSET-CLOTHES-%s.txt";
    private final String OUTFIT_LIST_BASE = "CLOSET-OUTFIT-%s.txt";
    private String owner = "";

    private List<Clothing> mClothingList;
    private Map<Category, List<Clothing>> mClothingMap;
    private List<Outfit> mOutfitList;

    private Closet() {
        Log.d("Closet", "Initializing");
        loadFromMemory();
    }

    private String getClothesListFileName() {
        return String.format(CLOTHES_LIST_BASE, owner);
    }

    private String getOutfitListFileName() {
        return String.format(OUTFIT_LIST_BASE, owner);
    }

    private void addClothing(Clothing clothing) {
        clothing.setId(mClothingList.size());
        mClothingList.add(clothing);
        mClothingMap.get(clothing.getCategory()).add(clothing);
    }

    // NOTE - this needs to be run after loading clothing from memory
    private void loadOutfitsFromMemory() {
        mOutfitList = new ArrayList<>();

        StringBuffer sBuff = new StringBuffer("");
        String[] tempSplit;

        try {
            FileInputStream fin = App.getContext().openFileInput(getOutfitListFileName());
            InputStreamReader isr = new InputStreamReader ( fin ) ;
            BufferedReader buffReader = new BufferedReader ( isr ) ;

            String readString = buffReader.readLine ( ) ;
            while ( readString != null ) {
                sBuff.append(readString);
                readString = buffReader.readLine ( ) ;
            }
            fin.close();
            isr.close () ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        tempSplit = sBuff.toString().trim().split("/split/");

        int words = 0;

        int numCategories = Category.values().length;

        while(words + numCategories - 1 < tempSplit.length) {
            Map<Category, Clothing> newOutfitMap = new HashMap<>();
            for (int i = 0; i < numCategories; ++i) {
                String v = tempSplit[i];
                int val = Integer.valueOf(v);
                if (val != -1) {
                    Clothing clothing = mClothingList.get(val);
                    newOutfitMap.put(clothing.getCategory(), clothing);
                }
            }
            Outfit outfit = new Outfit(newOutfitMap);
            mOutfitList.add(outfit);
            words = words + numCategories;
        }
    }

    private void loadClothesFromMemory() {
        mClothingList = new ArrayList<>();
        mClothingMap = new HashMap<>();

        for (Category c : Category.values()) {
            mClothingMap.put(c, new ArrayList<Clothing>());
        }

        StringBuffer sbuff = new StringBuffer("");
        String[] tempSplit;

        try {
            FileInputStream fin = App.getContext().openFileInput(getClothesListFileName());
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
            // TODO - why aren't we saving the number of times worn?
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

    private void loadFromMemory() {
        loadClothesFromMemory();
        loadOutfitsFromMemory();
    }

    private void resetClothingCloset() {
        try {
            FileOutputStream fos = App.getContext().openFileOutput(getClothesListFileName(), Context.MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();
        } catch(Exception e) {
            Log.e("ClothingActivity", e.toString());
        }
    }

    private void resetOutfitCloset() {
        try {
            FileOutputStream fos = App.getContext().openFileOutput(getOutfitListFileName(), Context.MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();
        } catch(Exception e) {
            Log.e("ClothingActivity", e.toString());
        }
    }

    private void resetMemory() {
        resetClothingCloset();
        resetOutfitCloset();
    }

    private void saveClothingToMemory(Clothing clothing) {
        // TODO - why aren't we saving the number of times worn?
        String string = clothing.getName() + "/split/" + clothing.getCategory().toString() + "/split/"
                + clothing.getWarmth().toString() + "/split/" + clothing.getOccasion().toString() + "/split/"
                + clothing.getCleanliness().toString() + "/split/" + clothing.getColor() + "/split/"
                + clothing.getPhoto() + "/split/";
        try {
            FileOutputStream fos = App.getContext().openFileOutput(getClothesListFileName(), Context.MODE_APPEND);
            fos.write(string.getBytes());
            fos.close();
        } catch(Exception e) {
            Log.e("ClothingActivity", e.toString());
        }
    }

    public void saveOutfitToMemory(Outfit outfit) {
        Map<Category, Clothing> outfitMap = outfit.getClothingMap();
        StringBuffer strBuff = new StringBuffer();

        for (Category c : Category.values()) {
            if (outfitMap.containsKey(c)) {
                 strBuff.append(outfitMap.get(c).getId() + "/split/");
            } else {
                 strBuff.append(-1 + "/split/");
            }
        }

        String string = strBuff.toString();
        try {
            FileOutputStream fos = App.getContext().openFileOutput(getOutfitListFileName(), Context.MODE_APPEND);
            fos.write(string.getBytes());
            fos.close();
        } catch(Exception e) {
            Log.e("ClothingActivity", e.toString());
        }

    }

    private void saveAllOutfitsToMemory() {
        for (Outfit o: mOutfitList) {
            saveOutfit(o);
        }
    }

    private void saveAllClothingToMemory() {
        for (Clothing c : mClothingList) {
            saveClothingToMemory(c);
        }
    }

    // Saves clothing to internal storage but also adds to our local lists
    public void saveClothing(Clothing clothing) {
        addClothing(clothing);
        saveClothingToMemory(clothing);
    }

    // Saves an outfit to both internal storage and local lists
    public void saveOutfit(Outfit outfit) {
        mOutfitList.add(outfit);
        saveOutfitToMemory(outfit);
    }

    public List<Clothing> getClothesFromCategory(Category c) {
        return mClothingMap.get(c);
    }


    public static Closet getInstance() {
        if(mInstance == null) {
            mInstance = new Closet();
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

    public void doLaundry() {
        for (Clothing c : mClothingList) {
            c.setTimesWorn(0);
        }
        update();
    }

    public void reset() {
        resetMemory();
        mClothingList = new ArrayList<>();
        mClothingMap = new HashMap<>();
        for (Category c : Category.values()) {
            mClothingMap.put(c, new ArrayList<Clothing>());
        }
        mOutfitList = new ArrayList<>();
    }

    public void update() {
        resetMemory();
        saveAllClothingToMemory();
    }

    public List<Clothing> getClothingList() {
        return mClothingList;
    }

    public List<Outfit> getOutfitList() {
        return mOutfitList;
    }
    //public void setClothingList(List<Clothing> clothingList) {
    //    mClothingList = clothingList;
    //}

    public int size() {
        return mClothingList.size();
    }

    public void setOwner(String owner) {
        // Save everything to memory first
        resetMemory();
        saveAllClothingToMemory();
        saveAllOutfitsToMemory();
        // Now switch to another closet
        this.owner = owner;
        loadFromMemory();
    }

    public String getOwner() {
        return owner;
    }
}
