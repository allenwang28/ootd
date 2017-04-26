package com.qinglenmeson.ootd;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileOutputStream;

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
                String filename = "CLOSET.txt";
                String string = clothing.getName() + "/split/" + clothing.getCategory().toString() + "/split/"
                        + clothing.getWarmth().toString() + "/split/" + clothing.getOccasion().toString() + "/split/"
                        + clothing.getCleanliness().toString() + "/split/" + clothing.getColor() + "/split/"
                        + clothing.getPhoto() + "/split/";
                try {
                    FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                    fos.write(string.getBytes());
                    fos.close();
                } catch(Exception e) {
                    Log.e("ClothingActivity", e.toString());
                }
                Log.d("ClothingActivity", String.format("Added clothing %s \n\n Resetting the clothing object now", clothing.toString()));
                clothing = new Clothing();
                Log.d("ClothingActivity", String.format("Clothing is now %s", clothing.toString()));
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

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
