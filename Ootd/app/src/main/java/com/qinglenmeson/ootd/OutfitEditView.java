package com.qinglenmeson.ootd;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
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

import com.skydoves.colorpickerview.ColorPickerView;

/**
 * Created by Qing Wang on 4/27/2017.
 */

public class OutfitEditView extends LinearLayout {
    private Outfit outfit;

    // Views

    // TODO - make clothing type unchangeable

    private OutfitGeneratedView outfitGeneratedView;

    public OutfitEditView(Context context, Outfit outfit) {
        super(context);
        initializeViews(context, outfit);
    }

    public OutfitEditView(Context context) {
        super(context);
    }

    public OutfitEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OutfitEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void resetViews(final Context context) {
        outfitGeneratedView = (OutfitGeneratedView)findViewById(R.id.outfit_edit_view_generated_view);
        outfitGeneratedView.setOutfit(outfit);
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    private void initializeViews(final Context context, final Outfit outfit) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.outfit_edit_view, this);
        Log.d("OutfitEditView", "Initialized View");

        this.outfit = outfit;
        resetViews(context);
    }

    // Utility functions
    private void editOutfitName(String name) {
        Log.d("OutfitEditView", String.format("Changing outfit %s to %s", outfit.getName(), name));
        this.outfit.setName(name);
    }

    // Getters and Setters
    public void setOutfit(Outfit outfit) {
        // We need to do this to update everything
        if (this.outfit == null) {
            this.outfit = outfit;
            initializeViews(this.getContext(), this.outfit);
        } else {
            this.outfit = outfit;
            resetViews(this.getContext());
        }
    }

    public Outfit getOutfit() {
        return this.outfit;
    }
}
