package com.qinglenmeson.ootd;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AddClothingActivity extends AppCompatActivity {
    private ClothingAddView clothingAddView;
    private Clothing clothing;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothing);

        clothingAddView = (ClothingAddView) findViewById(R.id.add_clothing_clothing_view);
        clothing = new Clothing();

        clothingAddView.setClothing(clothing);
        addButton = (Button)findViewById(R.id.add_clothing_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothing = clothingAddView.getClothing();
                clothing.setPhoto(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + clothing.getName() + ".jpg");
                Closet closet = Closet.getInstance();
                closet.saveClothing(clothing);

                Log.d("AddClothingActivity", String.format("Added clothing %s \n\n Resetting the clothing object now", clothing.toString()));
                clothing = new Clothing();
                Log.d("AddClothingActivity", String.format("Clothing is now %s", clothing.toString()));
                clothingAddView.setClothing(clothing);
                // Show notification
                Snackbar.make(findViewById(R.id.add_clothing_add_button), "Clothing added",
                        Snackbar.LENGTH_SHORT)
                        .show();
                goBack();
            }
        });

    }

    public void takePicture(View view) {
        Intent intent = new Intent(this, AndroidCameraApi.class);
        intent.putExtra("clothingName",clothing.getName());
        startActivity(intent);
    }

    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
