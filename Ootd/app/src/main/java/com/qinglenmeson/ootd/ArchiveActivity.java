package com.qinglenmeson.ootd;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
}
