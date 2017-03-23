package com.qinglenmeson.ootd;

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
                clothingEditView.setClothing(clothing);
            }
        });

    }

}
