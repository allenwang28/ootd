package com.qinglenmeson.ootd;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView clothingListView;
    private RecyclerView outfitListView;
    private Closet closet;
    private static String owner = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayoutManager clothingLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager outfitLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        closet = Closet.getInstance();
        owner = closet.getOwner();

        //Title
        TextView title = (TextView) findViewById(R.id.main_title);
        if(owner.equals("")) {
            title.setText("Default Closet");
        } else {
            title.setText(owner + "'s Closet");
        }

        // Set up the recyclerviews
        clothingListView = (RecyclerView) findViewById(R.id.main_ClothingList);
        clothingListView.setLayoutManager(clothingLayoutManager);

        clothingListView.setNestedScrollingEnabled(false);

        ClothingPreviewAdapter clothingPreviewAdapter = new ClothingPreviewAdapter(this, closet.getClothingList());
        clothingListView.setAdapter(clothingPreviewAdapter);

        outfitListView = (RecyclerView) findViewById(R.id.main_OutfitList);
        outfitListView.setLayoutManager(outfitLayoutManager);
        outfitListView.setNestedScrollingEnabled(false);

        List<Outfit> outfitList = closet.getOutfitList();
        OutfitPreviewAdapter outfitPreviewAdapter = new OutfitPreviewAdapter(this, outfitList);
        outfitListView.setAdapter(outfitPreviewAdapter);

    }

    public void openArchives(View view) {
        Intent intent = new Intent(this, ArchiveActivity.class);
        startActivity(intent);
    }

    public void openAddClothing(View view) {
        Intent intent = new Intent(this, ClothingActivity.class);
        startActivity(intent);
    }

    public void openGenerateOutfit(View view) {
        Intent intent = new Intent(this, GenerateOutfitActivity.class);
        startActivity(intent);
    }
    
    public void openPastOutfits(View view) {
        // TODO
    }

    public void reset(View view) {
        closet.reset();
        recreate();
    }

    public void laundry(View view) {
        closet.doLaundry();
        Log.d("MainActivity", "Did laundry");
        Snackbar.make(findViewById(R.id.activity_main), "Laundry Done",
                Snackbar.LENGTH_SHORT)
                .show();
    }

    public void editTitle(View view) {
        Intent intent = new Intent(this, ChangeUserActivity.class);
        startActivity(intent);
    }
}
