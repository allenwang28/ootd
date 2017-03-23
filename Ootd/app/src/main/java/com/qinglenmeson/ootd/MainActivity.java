package com.qinglenmeson.ootd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView clothingListView;
    private RecyclerView outfitListView;

    private List<Clothing> closet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayoutManager clothingLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager outfitLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        // TODO - find the real way to get this list from storage or something...
        closet = new ArrayList<>();
        closet.add(new Clothing("Blouse", Category.BLOUSE, Warmth.WARM, Occasion.CASUAL));
        closet.add(new Clothing("Nike Shorts", Category.PANTS, Warmth.WARM, Occasion.ATHLETIC));
        closet.add(new Clothing("Pants", Category.PANTS, Warmth.WARM, Occasion.CASUAL));
        closet.add(new Clothing("Jacket", Category.SWEATER, Warmth.WARM, Occasion.CASUAL));
        closet.add(new Clothing("Swimming Trunks", Category.SWEATER, Warmth.WARM, Occasion.SWIM));
        closet.add(new Clothing("Jeans", Category.PANTS, Warmth.WARM, Occasion.ATHLETIC));

        // Lets the recyclerviews be horizontal
        clothingListView = (RecyclerView) findViewById(R.id.main_ClothingList);
        clothingListView.setLayoutManager(clothingLayoutManager);

        ClothingPreviewAdapter clothingPreviewAdapter = new ClothingPreviewAdapter(this, closet);

        clothingListView.setAdapter(clothingPreviewAdapter);

        outfitListView = (RecyclerView) findViewById(R.id.main_OutfitList);
        outfitListView.setLayoutManager(outfitLayoutManager);

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
