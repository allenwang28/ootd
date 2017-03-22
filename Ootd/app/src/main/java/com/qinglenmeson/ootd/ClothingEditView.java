package com.qinglenmeson.ootd;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public class ClothingEditView extends LinearLayout {
    private Clothing clothing;

    private TextView nameView;

    /*
    private EditText editName;
    // TODO - add image thing too

    private EditText editTimesWorn;
    private Button
    */
    public ClothingEditView(Context context, Clothing clothing) {
        super(context);
        this.clothing = clothing;
        initializeViews(context, clothing);
    }

    public ClothingEditView(Context context, AttributeSet attrs, Clothing clothing) {
        super(context, attrs);
        initializeViews(context, clothing);
    }

    public ClothingEditView(Context context, AttributeSet attrs, int defStyleAttr, Clothing clothing) {
        super(context, attrs, defStyleAttr);
        initializeViews(context, clothing);
    }

    private void initializeViews(Context context, Clothing clothing) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.clothingedit_view, this);
        Log.d("clothingEditView", "Initialized View");
        nameView = (TextView) this.findViewById(R.id.clothingedit_name);
        nameView.setText(clothing.getName());
        nameView.setBackgroundColor(Color.BLACK);
        nameView.setTextColor(Color.WHITE);
    }

    public void setClothing(Clothing clothing) {
        this.clothing = clothing;
    }

    public Clothing getClothing() {
        return this.clothing;
    }


}
