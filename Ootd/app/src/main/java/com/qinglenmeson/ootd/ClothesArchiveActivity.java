package com.qinglenmeson.ootd;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClothesArchiveActivity extends AppCompatActivity {
    Button backToMain;
    Button remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_archive);

        Closet closet = Closet.getInstance();
        ViewPager viewPager = (ViewPager) findViewById(R.id.clothing_archive_activity_view_pager);

        ClothingAdapter clothingAdapter = new ClothingAdapter(this, closet.getClothingList());
        viewPager.setAdapter(clothingAdapter);

        backToMain = (Button)findViewById(R.id.clothing_archive_activity_backToMain);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closet closet = Closet.getInstance();
                if(closet.clothingListSize() > 0) {
                    ViewPager viewPager = (ViewPager) findViewById(R.id.clothing_archive_activity_view_pager);
                    ClothingAdapter adapter = (ClothingAdapter) viewPager.getAdapter();
                    ClothingEditView clothingEditView = (ClothingEditView) adapter.instantiateItem(viewPager, viewPager.getCurrentItem());
                    Clothing clothing = clothingEditView.getClothing();
                    //clothing.setPhoto(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + clothing.getName() + ".jpg");
                    closet.removeClothing(clothing);
                    closet.saveClothing(clothing);

                    clothing = new Clothing();
                    clothingEditView.setClothing(clothing);
                    // Show notification
                    Snackbar.make(findViewById(R.id.clothing_archive_activity_view_pager), "Clothing Edited",
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
                goBack();
            }
        });

        remove = (Button)findViewById(R.id.clothing_archive_activity_remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closet closet = Closet.getInstance();
                if(closet.clothingListSize() > 0) {
                    ViewPager viewPager = (ViewPager) findViewById(R.id.clothing_archive_activity_view_pager);
                    ClothingAdapter adapter = (ClothingAdapter) viewPager.getAdapter();
                    ClothingEditView clothingEditView = (ClothingEditView) adapter.instantiateItem(viewPager, viewPager.getCurrentItem());
                    Clothing clothing = clothingEditView.getClothing();
                    //clothing.setPhoto(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + clothing.getName() + ".jpg");
                    closet.removeClothing(clothing);

                    adapter.notifyDataSetChanged();
                    // Show notification
                    Snackbar.make(findViewById(R.id.clothing_archive_activity_view_pager), "Clothing Removed",
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
                goBack();
            }
        });

    }


    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
