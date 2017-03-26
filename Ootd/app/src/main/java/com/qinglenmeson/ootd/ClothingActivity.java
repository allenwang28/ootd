package com.qinglenmeson.ootd;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClothingActivity extends AppCompatActivity {
    private ClothingEditView clothingEditView;
    private Clothing clothing;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        clothingEditView = (ClothingEditView)findViewById(R.id.clothing_clothing_view);
        clothing = new Clothing();

        clothingEditView.setClothing(clothing);
        addButton = (Button)findViewById(R.id.clothing_add_view);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothing = clothingEditView.getClothing();
                // TODO - save the clothing somewhere else
                // Something like...
                // list.add(clothing);
                clothing = new Clothing();
                //TODO - save the photo into Clothing item
                //clothing.setPhoto();
                clothingEditView.setClothing(clothing);

                // Show notification
                Snackbar.make(findViewById(R.id.clothing_add_view), "Clothing added",
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

    }


    public void takePicture(View view) {
        Intent intent = new Intent(this, AndroidCameraApi.class);
        startActivity(intent);
    }



}
