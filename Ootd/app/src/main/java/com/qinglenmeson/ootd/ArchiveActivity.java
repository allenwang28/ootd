package com.qinglenmeson.ootd;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.R.attr.fragment;

public class ArchiveActivity extends AppCompatActivity {
    Button backToMain;
    Button remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        Closet closet = Closet.getInstance();
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        ClothingAdapter clothingAdapter = new ClothingAdapter(this, closet.getClothingList());
        viewPager.setAdapter(clothingAdapter);

        backToMain = (Button)findViewById(R.id.archive_backToMain);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closet closet = Closet.getInstance();
                if(closet.size() > 0) {
                    ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
                    ClothingAdapter adapter = (ClothingAdapter) viewPager.getAdapter();
                    ClothingEditView clothingEditView = (ClothingEditView) adapter.instantiateItem(viewPager, viewPager.getCurrentItem());
                    Clothing clothing = clothingEditView.getClothing();
                    //clothing.setPhoto(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + clothing.getName() + ".jpg");
                    closet.removeClothing(clothing);
                    closet.saveClothing(clothing);

                    clothing = new Clothing();
                    clothingEditView.setClothing(clothing);
                    // Show notification
                    //TODO: Show this? view_pager doesn't make it show
                    Snackbar.make(findViewById(R.id.view_pager), "Clothing Edited",
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
                goBack();
            }
        });

        remove = (Button)findViewById(R.id.archive_remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closet closet = Closet.getInstance();
                if(closet.size() > 0) {
                    ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
                    ClothingAdapter adapter = (ClothingAdapter) viewPager.getAdapter();
                    ClothingEditView clothingEditView = (ClothingEditView) adapter.instantiateItem(viewPager, viewPager.getCurrentItem());
                    Clothing clothing = clothingEditView.getClothing();
                    //clothing.setPhoto(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + clothing.getName() + ".jpg");
                    closet.removeClothing(clothing);

                    adapter.notifyDataSetChanged();
                    // Show notification
                    Snackbar.make(findViewById(R.id.view_pager), "Clothing Removed",
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
                goBack();
            }
        });

    }

    //TODO: ADD CAMERA

    /*
    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    */


    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
