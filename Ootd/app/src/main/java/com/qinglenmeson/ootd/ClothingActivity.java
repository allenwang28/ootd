package com.qinglenmeson.ootd;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                String filename = "CLOTHES";
                //TODO: what is format of these toStrings?
                String string = clothing.getName() + "\n" + clothing.getCategory().toString() + "\n"
                        + clothing.getWarmth().toString() + "\n" + clothing.getOccasion().toString() + "\n"
                        + clothing.getCleanliness().toString() + "\n" + clothing.getColor() + "\n"
                        + clothing.getPhoto().toString() + "\n";
                try {
                    FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                    fos.write(string.getBytes());
                    fos.close();
                } catch(Exception e) {
                }
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
