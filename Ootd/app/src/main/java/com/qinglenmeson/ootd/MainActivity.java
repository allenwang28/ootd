package com.qinglenmeson.ootd;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView clothingListView;
    private RecyclerView outfitListView;
    private Closet closet;
    private static String owner = "";
    private static Integer zipCode = 78705;
    String weatherInfo = "";
    Day day;
    private static boolean zipChange = false;

    private OutfitPreviewView todaysOutfit;

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

        day = Day.getInstance();
        if(day == null || zipChange == true) {
            //TODO: Make this work for multiple zip codes
            try {
                weatherInfo = new GetWeatherInfo().execute().get();
                day = Day.getInstance(weatherInfo);
                zipChange = false;
            } catch (Exception e) {

            }
        }

        TextView weatherTitle = (TextView) findViewById(R.id.main_weather);
        weatherTitle.setText("Weather for " + zipCode + " area on " + day.getMonth() + "/" + day.getDay());

        ImageView weatherIcon = (ImageView) findViewById(R.id.main_weatherImage);
        day.setIcon(weatherIcon);

        TextView dayLow= (TextView) findViewById(R.id.main_dayLow);
        TextView dayHigh = (TextView) findViewById(R.id.main_dayHigh);

        dayLow.setText("Low: " + day.getTempLow() + " F");
        dayHigh.setText("High: " + day.getTempHigh() + " F");

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
        OutfitPreviewAdapter outfitPreviewAdapter = new OutfitPreviewAdapter(this);
        outfitListView.setAdapter(outfitPreviewAdapter);

        todaysOutfit = (OutfitPreviewView)findViewById(R.id.main_todaysoutfit);
        todaysOutfit.setOutfit(closet.getTodaysOutfit());
    }

    public void openArchives(View view) {
        Intent intent = new Intent(this, ClothesArchiveActivity.class);
        startActivity(intent);
    }

    public void openAddClothing(View view) {
        Intent intent = new Intent(this, AddClothingActivity.class);
        startActivity(intent);
    }

    public void openGenerateOutfit(View view) {
        Intent intent = new Intent(this, GenerateOutfitActivity.class);
        startActivity(intent);
    }
    
    public void openPastOutfits(View view) {
        Intent intent = new Intent(this, OutfitArchiveActivity.class);
        startActivity(intent);
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

    public void editZip(View view) {
        Intent intent = new Intent(this, ChangeZipActivity.class);
        startActivity(intent);
    }

    private int KtoF(int K) {
        return (int) (K*9/5 - 459.67);
    }

    public static int getZip() {
        return zipCode;
    }

    public static void setZip(int zip) {
        if(zipCode != zip) {
            zipCode = zip;
            zipChange = true;
        }
    }
}
