package com.qinglenmeson.ootd;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ArchiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        Closet closet = Closet.getInstance();
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        ClothingAdapter clothingAdapter = new ClothingAdapter(this, closet.getClothingList());
        viewPager.setAdapter(clothingAdapter);
    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
