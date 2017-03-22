package com.qinglenmeson.ootd;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public class ClothingEditView extends LinearLayout implements AdapterView.OnItemSelectedListener {
    private Clothing clothing;

    // Views held
    private EditText editName;
    private Spinner cleanlinessSpinner;

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
        Log.d("ClothingEditView", "Initialized View");

        // Allow the EditText to change the name of the clothing
        editName = (EditText) this.findViewById(R.id.clothingedit_EditName);
        editName.setText(clothing.getName(), TextView.BufferType.EDITABLE);
        editName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Log.d("ClothingEditView", "Editor Action Done");
                    editClothingName(v.getText().toString());
                    return true;
                }
                return false;
            }
        });

        // Allow the ability to change the cleanliness
        cleanlinessSpinner = (Spinner) this.findViewById(R.id.clothingedit_ChangeCleanliness);
        ArrayAdapter<CharSequence> cleanlinessAdapter = ArrayAdapter.createFromResource(context,
                R.array.cleanliness_array, android.R.layout.simple_spinner_dropdown_item);
        cleanlinessSpinner.setAdapter(cleanlinessAdapter);
        cleanlinessSpinner.setOnItemSelectedListener(this);
    }

    // Utility functions
    private void editClothingName(String name) {
        Log.d("ClothingEditView", String.format("Changing clothing %s to %s", clothing.getName(), name));
        this.clothing.setName(name);
    }

    // Getters and Setters
    public void setClothing(Clothing clothing) {
        this.clothing = clothing;
    }

    public Clothing getClothing() {
        return this.clothing;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("ClothingEdit","Item selected");
        switch(parent.getId()) {
            case R.id.clothingedit_ChangeCleanliness:
                String newCleanlinessName = (String)parent.getItemAtPosition(position);
                Cleanliness newCleanliness = Cleanliness.fromString(newCleanlinessName);
                Log.d("ClothingEdit", String.format("Changing Cleanliness of %s from %s to %s",
                        clothing.getName(), clothing.getCleanliness().toString(), newCleanlinessName));
                clothing.setCleanliness(newCleanliness);
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
