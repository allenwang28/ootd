package com.qinglenmeson.ootd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GenerateOutfitActivity extends AppCompatActivity {
    private Button shuffleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_outfit);

        //create day object for today

        shuffleButton = (Button)findViewById(R.id.generate_outfit_shuffle);
        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO - fill this in
                //generate outfit for today;
                //create new view, show outfit to user
            }
        });

        // TODO - add to the XML how we're going to create a preview of the outfit
    }
}
