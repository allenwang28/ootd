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

    private final String CLOTHES_LIST_BASE = "CLOSET-CLOTHES-%s.txt";
    private final String OUTFIT_LIST_BASE = "CLOSET-OUTFIT-%s.txt";
    private final String TODAYS_OUTFIT_BASE = "CLOSET-TODAY-%s.txt";
    private String owner = "";

    private List<Clothing> mClothingList;
    private Map<Category, List<Clothing>> mClothingMap;
    private Map<Clothing, List<Outfit>> mClothingOutfitMap;
    private List<Outfit> mOutfitList;

    private Outfit todaysOutfit;

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

    private String getTodaysOutfitFileName() {
        return String.format(TODAYS_OUTFIT_BASE, owner);
    }

    private void addClothing(Clothing clothing) {
        clothing.setId(mClothingList.size());
        mClothingList.add(clothing);
        mClothingMap.get(clothing.getCategory()).add(clothing);
        mClothingOutfitMap.put(clothing, new ArrayList<Outfit>());
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

        while(words + numCategories < tempSplit.length) {
            Map<Category, Clothing> newOutfitMap = new HashMap<>();
            String outfitName = tempSplit[words];
            for (int i = 1; i <= numCategories; ++i) {
                String v = tempSplit[words + i];
                int val = Integer.valueOf(v);
                if (val != -1) {
                    Clothing clothing = mClothingList.get(val);
                    newOutfitMap.put(clothing.getCategory(), clothing);
                }
            }
            Outfit outfit = new Outfit(newOutfitMap);
            outfit.setName(outfitName);
            addOutfit(outfit);
            words = words + numCategories + 1;
        }
    }

    private void loadClothesFromMemory() {
        mClothingList = new ArrayList<>();
        mClothingMap = new HashMap<>();
        mClothingOutfitMap = new HashMap<>();

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

        while(words+7 < tempSplit.length) {
            String name = tempSplit[words];
            String category = tempSplit[words+1];
            String warmth = tempSplit[words+2];
            String occasion = tempSplit[words+3];
            String cleanliness = tempSplit[words+4];
            String color = tempSplit[words+5];
            String photo = tempSplit[words+6];
            String timesWorn = tempSplit[words+7];
            words = words + 8;

            Clothing clothing = new Clothing(name,
                    Category.fromString(category),
                    Warmth.fromString(warmth),
                    Occasion.fromString(occasion),
                    Cleanliness.fromString(cleanliness),
                    Integer.parseInt(color),
                    photo);
            clothing.setTimesWorn(Integer.parseInt(timesWorn));

            addClothing(clothing);
        }
    }

    private void loadTodaysOutfitFromMemory() {
        todaysOutfit = new Outfit();
        StringBuffer sbuff = new StringBuffer("");
        String[] tempSplit;

        try {
            FileInputStream fin = App.getContext().openFileInput(getTodaysOutfitFileName());
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
        if (tempSplit.length < 3) { return; }
        Day dayObject = Day.getInstance();
        int month = Integer.parseInt(tempSplit[0]);
        int day = Integer.parseInt(tempSplit[1]);
        int idx = Integer.parseInt(tempSplit[2]);
        if (month == dayObject.getMonth() && day == dayObject.getDay()) {
            if (idx >= 0 && idx < mOutfitList.size()) {
                todaysOutfit = mOutfitList.get(idx);
                return;
            }
        }
        todaysOutfit = new Outfit();
    }

    private void loadFromMemory() {
        loadClothesFromMemory();
        loadOutfitsFromMemory();
        loadTodaysOutfitFromMemory();
    }

    private void resetClothingCloset() {
        try {
            FileOutputStream fos = App.getContext().openFileOutput(getClothesListFileName(), Context.MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();
        } catch(Exception e) {
            Log.e("Closet", e.toString());
        }
    }

    private void resetOutfitCloset() {
        try {
            FileOutputStream fos = App.getContext().openFileOutput(getOutfitListFileName(), Context.MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();
        } catch(Exception e) {
            Log.e("Closet", e.toString());
        }
    }

    private void resetTodaysOutfitFromMemory() {
        try {
            FileOutputStream fos = App.getContext().openFileOutput(getTodaysOutfitFileName(), Context.MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();
        } catch(Exception e) {
            Log.e("Closet", e.toString());
        }
    }

    private void resetMemory() {
        resetClothingCloset();
        resetOutfitCloset();
        resetTodaysOutfitFromMemory();
    }

    private void saveClothingToMemory(Clothing clothing) {
        String string = clothing.getName() + "/split/" + clothing.getCategory().toString() + "/split/"
                + clothing.getWarmth().toString() + "/split/" + clothing.getOccasion().toString() + "/split/"
                + clothing.getCleanliness().toString() + "/split/" + clothing.getColor() + "/split/"
                + clothing.getPhoto() + "/split/" + clothing.getTimesWorn() + "/split/";
        try {
            FileOutputStream fos = App.getContext().openFileOutput(getClothesListFileName(), Context.MODE_APPEND);
            fos.write(string.getBytes());
            fos.close();
        } catch(Exception e) {
            Log.e("Closet", e.toString());
        }
    }

    public void saveOutfitToMemory(Outfit outfit) {
        Map<Category, Clothing> outfitMap = outfit.getClothingMap();
        StringBuffer strBuff = new StringBuffer();

        strBuff.append(outfit.getName() + "/split/");
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
            Log.e("Closet", e.toString());
        }
    }
    
    public void saveTodaysOutfitToMemory() {
        resetTodaysOutfitFromMemory();
        StringBuffer strBuff = new StringBuffer();
        Day day = Day.getInstance();
        strBuff.append(day.getMonth() + "/split/" + day.getDay() + "/split/" + mOutfitList.indexOf(todaysOutfit));

        String string = strBuff.toString();
        try {
            FileOutputStream fos = App.getContext().openFileOutput(getTodaysOutfitFileName(), Context.MODE_APPEND);
            fos.write(string.getBytes());
            fos.close();
        } catch(Exception e) {
            Log.e("Closet", e.toString());
        }
    }

    public void clearTodaysOutfit() {
        todaysOutfit.decrementWear();
        todaysOutfit = new Outfit();
        resetTodaysOutfitFromMemory();
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

    public void addOutfit(Outfit outfit) {
        mOutfitList.add(outfit);
        Map<Category, Clothing> map = outfit.getClothingMap();
        for (Category category : Category.values()) {
            if (map.containsKey(category)) {
                Clothing clothing = map.get(category);
                List<Outfit> correspondingOutfitList = mClothingOutfitMap.get(clothing);
                correspondingOutfitList.add(outfit);
            }
        }
    }

    // Saves an outfit to both internal storage and local lists
    public void saveOutfit(Outfit outfit) {
        addOutfit(outfit);
        saveOutfitToMemory(outfit);
    }

    public Outfit getTodaysOutfit() {
        return todaysOutfit;
    }

    public void saveTodaysOutfit(Outfit outfit) {
        if (todaysOutfit != null) {
            todaysOutfit.decrementWear();
        }
        outfit.incrementWear();
        todaysOutfit = outfit;
        saveTodaysOutfitToMemory();
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
        // If we remove an article of clothing it should be removed from the outfit as well
        List<Outfit> correspondingOutfits = mClothingOutfitMap.get(c);
        for (Outfit outfit : correspondingOutfits) {
            outfit.getClothingMap().remove(c.getCategory());
        }

        mClothingList.remove(c);
        mClothingMap.get(c.getCategory()).remove(c);

        resetClothingCloset();
        for (Clothing mC : mClothingList) {
            saveClothingToMemory(mC);
        }
    }

    public void removeOutfit(Outfit outfit) {
        if (!mOutfitList.contains(outfit)) {
            return;
        }
        if (outfit == todaysOutfit) {
            resetTodaysOutfitFromMemory();
            todaysOutfit = new Outfit();
        }
        mOutfitList.remove(outfit);
        resetOutfitCloset();
        saveAllOutfitsToMemory();
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
        mClothingOutfitMap = new HashMap<>();
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

    public int clothingListSize() {
        return mClothingList.size();
    }
    public int outfitListSize() { return mOutfitList.size(); }

    public void setOwner(String owner) {
        // Save everything to memory first
        resetMemory();
        saveAllClothingToMemory();
        saveAllOutfitsToMemory();
        saveTodaysOutfitToMemory();
        // Now switch to another closet
        this.owner = owner;
        loadFromMemory();
    }

    public String getOwner() {
        return owner;
    }
}
