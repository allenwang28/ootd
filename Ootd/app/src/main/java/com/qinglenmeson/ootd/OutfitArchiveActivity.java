package com.qinglenmeson.ootd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OutfitArchiveActivity extends AppCompatActivity {
    private Button setTodaysOutfit;
    private Button remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit_archive);

        Closet closet = Closet.getInstance();
        ViewPager viewPager = (ViewPager) findViewById(R.id.outfit_archive_activity_view_pager);

        OutfitAdapter outfitAdapter = new OutfitAdapter(this);
        viewPager.setAdapter(outfitAdapter);

        setTodaysOutfit = (Button)findViewById(R.id.outfit_archive_activity_setTodaysOutfit);
        setTodaysOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closet closet = Closet.getInstance();

                if (closet.outfitListSize() > 0) {
                    ViewPager viewPager = (ViewPager) findViewById(R.id.outfit_archive_activity_view_pager);
                    OutfitAdapter adapter = (OutfitAdapter) viewPager.getAdapter();
                    OutfitEditView outfitEditView = (OutfitEditView) adapter.instantiateItem(viewPager, viewPager.getCurrentItem());
                    Outfit outfit = outfitEditView.getOutfit();
                    /*
                    closet.removeOutfit(outfit);
                    closet.saveOutfit(outfit);
                    */

                    closet.saveTodaysOutfit(outfit);

                    outfit = new Outfit();
                    outfitEditView.setOutfit(outfit);
                    // Show notification
                    Snackbar.make(findViewById(R.id.outfit_archive_activity_view_pager), "Today's Outfit Updated",
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
                goBack();
            }
        });

        remove = (Button)findViewById(R.id.outfit_archive_activity_remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Closet closet = Closet.getInstance();
                if(closet.outfitListSize() > 0) {
                    ViewPager viewPager = (ViewPager) findViewById(R.id.outfit_archive_activity_view_pager);
                    OutfitAdapter adapter = (OutfitAdapter) viewPager.getAdapter();
                    OutfitEditView outfitEditView = (OutfitEditView) adapter.instantiateItem(viewPager, viewPager.getCurrentItem());
                    Outfit outfit = outfitEditView.getOutfit();
                    //outfit.setPhoto(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + outfit.getName() + ".jpg");
                    closet.removeOutfit(outfit);

                    adapter.notifyDataSetChanged();
                    // Show notification
                    Snackbar.make(findViewById(R.id.outfit_archive_activity_view_pager), "Outfit Removed",
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
