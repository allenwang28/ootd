package com.qinglenmeson.ootd;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public class ClothingEditView extends LinearLayout implements AdapterView.OnItemSelectedListener {
    private Clothing clothing;

    // Views
    private EditText editName;
    private Spinner cleanlinessSpinner;
    private Spinner categorySpinner;
    private Spinner warmthSpinner;
    private Spinner occasionSpinner;
    private ImageView imageView;

    // TODO - Figure out if there's a better way to not just add the activity Seems very hacky
    public ClothingEditView(Context context, Clothing clothing) {
        super(context);
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

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    private void initializeViews(final Context context, Clothing clothing) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.clothingedit_view, this);
        Log.d("ClothingEditView", "Initialized View");

        this.clothing = clothing;

        // Allow the EditText to change the name of the clothing
        editName = (EditText) this.findViewById(R.id.clothingedit_EditName);
        editName.setText(clothing.getName(), TextView.BufferType.EDITABLE);
        editName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Log.d("ClothingEditView", "Editor Action Done");
                    editClothingName(v.getText().toString());
                    // Hide Keyboard
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
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
        cleanlinessSpinner.setSelection(clothing.getCleanliness().ordinal());

        // Allows the ability to change the category
        categorySpinner = (Spinner) this.findViewById(R.id.clothingedit_ChangeCategory);
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(context,
                R.array.category_array, android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(this);
        categorySpinner.setSelection(clothing.getCategory().ordinal());

        // Allows the ability to change the warmth
        warmthSpinner = (Spinner) this.findViewById(R.id.clothingedit_ChangeWarmth);
        ArrayAdapter<CharSequence> warmthAdapter = ArrayAdapter.createFromResource(context,
                R.array.warmth_array, android.R.layout.simple_spinner_dropdown_item);
        warmthSpinner.setAdapter(warmthAdapter);
        warmthSpinner.setOnItemSelectedListener(this);
        warmthSpinner.setSelection(clothing.getWarmth().ordinal());

        // Allows the ability to change the occasion
        occasionSpinner = (Spinner) this.findViewById(R.id.clothingedit_ChangeOccasion);
        ArrayAdapter<CharSequence> occasionAdapter = ArrayAdapter.createFromResource(context,
                R.array.occasion_array, android.R.layout.simple_spinner_dropdown_item);
        occasionSpinner.setAdapter(occasionAdapter);
        occasionSpinner.setOnItemSelectedListener(this);
        occasionSpinner.setSelection(clothing.getOccasion().ordinal());

        // Put the clothing's image on the ImageView
        imageView = (ImageView) this.findViewById(R.id.clothingedit_Image);
        imageView.setImageResource(R.drawable.blue_tshirt);

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
            case R.id.clothingedit_ChangeCategory:
                String newCategoryName = (String)parent.getItemAtPosition(position);
                Category newCategory = Category.fromString(newCategoryName);
                Log.d("ClothingEdit", String.format("Changing Category of %s from %s to %s",
                        clothing.getName(), clothing.getCategory().toString(), newCategoryName));
                clothing.setCategory(newCategory);
                break;
            case R.id.clothingedit_ChangeWarmth:
                String newWarmthName = (String)parent.getItemAtPosition(position);
                Warmth newWarmth = Warmth.fromString(newWarmthName);
                Log.d("ClothingEdit", String.format("Changing Warmth of %s from %s to %s",
                        clothing.getName(), clothing.getWarmth().toString(), newWarmthName));
                clothing.setWarmth(newWarmth);
                break;
            case R.id.clothingedit_ChangeOccasion:
                String newOccasionName = (String)parent.getItemAtPosition(position);
                Occasion newOccasion = Occasion.fromString(newOccasionName);
                Log.d("ClothingEdit", String.format("Changing Occasion of %s from %s to %s",
                        clothing.getName(), clothing.getOccasion().toString(), newOccasionName));
                clothing.setOccasion(newOccasion);
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
