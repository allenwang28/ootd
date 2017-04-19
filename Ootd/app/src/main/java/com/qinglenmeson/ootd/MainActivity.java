package com.qinglenmeson.ootd;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView clothingListView;
    private RecyclerView outfitListView;

    private List<Clothing> clothingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayoutManager clothingLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager outfitLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        clothingList = new ArrayList<>();
        String filename = "CLOSET";
        String temp = "";
        String[] tempSplit;
        int c;
        try {
            FileInputStream fin = openFileInput(filename);
            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
            tempSplit = temp.split("\n");

            int words = 0;
            while(words < tempSplit.length) {
                String name = tempSplit[words];
                String category = tempSplit[words+1];
                String warmth = tempSplit[words+2];
                String occasion = tempSplit[words+3];
                String cleanliness = tempSplit[words+4];
                String color = tempSplit[words+5];
                String photo = tempSplit[words+6];
                words = words + 7;
                //TODO: Photo take from File.toString
                //clothingList.add(new Clothing(name, Category.fromString(category), Warmth.fromString(warmth), Occasion.fromString(occasion),
                //        Cleanliness.fromString(cleanliness), Integer.getInteger(color), photo??));
            }
            fin.close();
        } catch(Exception e) {
        }
        /*
        for (int i = 0; i < 3; ++i) {
            clothingList.add(new Clothing("Blouse", Category.BLOUSE, Warmth.WARM, Occasion.CASUAL));
            clothingList.add(new Clothing("Nike Shorts", Category.PANTS, Warmth.WARM, Occasion.ATHLETIC));
            clothingList.add(new Clothing("Pants", Category.PANTS, Warmth.WARM, Occasion.CASUAL));
            clothingList.add(new Clothing("Jacket", Category.SWEATER, Warmth.WARM, Occasion.CASUAL));
            clothingList.add(new Clothing("Swimming Trunks", Category.SWEATER, Warmth.WARM, Occasion.SWIM));
            clothingList.add(new Clothing("Jeans", Category.PANTS, Warmth.WARM, Occasion.ATHLETIC));
            clothingList.add(new Clothing("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Category.PANTS, Warmth.WARM, Occasion.ATHLETIC));
        }
        */
        // Set up the recyclerviews
        clothingListView = (RecyclerView) findViewById(R.id.main_ClothingList);
        clothingListView.setLayoutManager(clothingLayoutManager);

        clothingListView.setNestedScrollingEnabled(false);

        ClothingPreviewAdapter clothingPreviewAdapter = new ClothingPreviewAdapter(this, clothingList);
        clothingListView.setAdapter(clothingPreviewAdapter);


        outfitListView = (RecyclerView) findViewById(R.id.main_OutfitList);
        outfitListView.setLayoutManager(outfitLayoutManager);

        outfitListView.setNestedScrollingEnabled(false);

        // TODO - fill this in after the Outfit class is created
        //OutfitPreviewAdapter outfitPreviewAdapter = new OutfitPreviewAdapter(this, outfitList);
        //
    }

    public void openArchives(View view) {
        Intent intent = new Intent(this, ArchiveActivity.class);
        startActivity(intent);
    }

    public void openAddClothing(View view) {
        Intent intent = new Intent(this, ClothingActivity.class);
        startActivity(intent);
    }
    
    public void openPastOutfits(View view) {
        // TODO
    }
}
