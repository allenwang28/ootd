package com.qinglenmeson.ootd;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.Map;

/**
 * Created by Allen Wang at 4/27/2017
 */
public class OutfitGeneratedView extends LinearLayout {
    private Outfit outfit;
    private Context context;
    private ImageView tshirtImageView;
    private ImageView shirtImageView;
    private ImageView shoesImageView;
    private ImageView jacketImageView;
    private ImageView sweaterImageView;
    private ImageView socksImageView;
    private ImageView pantsImageView;

    private TextView tshirtTextView;
    private TextView shirtTextView;
    private TextView shoesTextView;
    private TextView jacketTextView;
    private TextView sweaterTextView;
    private TextView socksTextView;
    private TextView pantsTextView;

    private EditText nameView;

    public void initializeViews(final Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.outfit_generated_view, this);
        Log.d("OutfitPreview", "Initialized View");
        tshirtImageView = (ImageView)findViewById(R.id.outfit_generated_view_Tshirt);
        shirtImageView = (ImageView)findViewById(R.id.outfit_generated_view_Shirt);
        shoesImageView = (ImageView)findViewById(R.id.outfit_generated_view_Shoes);
        jacketImageView = (ImageView)findViewById(R.id.outfit_generated_view_Jacket);
        sweaterImageView = (ImageView)findViewById(R.id.outfit_generated_view_Sweater);
        socksImageView = (ImageView)findViewById(R.id.outfit_generated_view_Socks);
        pantsImageView = (ImageView)findViewById(R.id.outfit_generated_view_Pants);

        tshirtTextView = (TextView)findViewById(R.id.outfit_generated_view_Tshirt_name);
        shirtTextView = (TextView)findViewById(R.id.outfit_generated_view_Shirt_name);
        shoesTextView = (TextView)findViewById(R.id.outfit_generated_view_Shoes_name);
        jacketTextView = (TextView)findViewById(R.id.outfit_generated_view_Jacket_name);
        sweaterTextView = (TextView)findViewById(R.id.outfit_generated_view_Sweater_name);
        socksTextView = (TextView)findViewById(R.id.outfit_generated_view_Socks_name);
        pantsTextView = (TextView)findViewById(R.id.outfit_generated_view_Pants_name);

        setStockViews();
    }

    public OutfitGeneratedView(Context context) {
        super(context);
        initializeViews(context);
    }

    public OutfitGeneratedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public OutfitGeneratedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }


    public OutfitGeneratedView(Context context, Outfit outfit) {
        super(context);
        this.outfit = outfit;
        setView();
    }

    public void setOutfit(Outfit outfit) {
        this.outfit = outfit;
        setView();
    }

    public Outfit getOutfit() {
        return outfit;
    }

    private void setStockViews() {
        tshirtImageView.setImageResource(R.drawable.tshirt);
        shirtImageView.setImageResource(R.drawable.shirt);
        shoesImageView.setImageResource(R.drawable.shoes);
        jacketImageView.setImageResource(R.drawable.jacket);
        sweaterImageView.setImageResource(R.drawable.sweater);
        socksImageView.setImageResource(R.drawable.socks);
        pantsImageView.setImageResource(R.drawable.pants);

        tshirtTextView.setText("None");
        shirtTextView.setText("None");
        shoesTextView.setText("None");
        jacketTextView.setText("None");
        sweaterTextView.setText("None");
        socksTextView.setText("None");
        pantsTextView.setText("None");

    }

    private void setView() {
        Map<Category, Clothing> outfitMap = outfit.getClothingMap();
        setStockViews();

        if (outfitMap.containsKey(Category.TSHIRT)) {
            Clothing c = outfitMap.get(Category.TSHIRT);
            String photo = c.getPhoto();
            File file = new File(photo);
            if (photo != null && !photo.equals("") && file.exists()) {
                tshirtImageView.setImageURI(android.net.Uri.parse(photo));
            }
            tshirtTextView.setText(c.getName());
        }
        if (outfitMap.containsKey(Category.SHIRT)) {
            Clothing c = outfitMap.get(Category.SHIRT);
            String photo = c.getPhoto();
            File file = new File(photo);
            if (photo != null && !photo.equals("") && file.exists()) {
                shirtImageView.setImageURI(android.net.Uri.parse(photo));
            }
            shirtTextView.setText(c.getName());
        }
        if (outfitMap.containsKey(Category.SHOES)) {
            Clothing c = outfitMap.get(Category.SHOES);
            String photo = c.getPhoto();
            File file = new File(photo);
            if (photo != null && !photo.equals("") && file.exists()) {
                shoesImageView.setImageURI(android.net.Uri.parse(photo));
            }
            shoesTextView.setText(c.getName());
        }
        if (outfitMap.containsKey(Category.JACKET)) {
            Clothing c = outfitMap.get(Category.JACKET);
            String photo = c.getPhoto();
            File file = new File(photo);
            if (photo != null && !photo.equals("") && file.exists()) {
                jacketImageView.setImageURI(android.net.Uri.parse(photo));
            }
            jacketTextView.setText(c.getName());
        }
        if (outfitMap.containsKey(Category.SWEATER)) {
            Clothing c = outfitMap.get(Category.SWEATER);
            String photo = c.getPhoto();
            File file = new File(photo);
            if (photo != null && !photo.equals("") && file.exists()) {
                sweaterImageView.setImageURI(android.net.Uri.parse(photo));
            }
            sweaterTextView.setText(c.getName());
        }
        if (outfitMap.containsKey(Category.SOCKS)) {
            Clothing c = outfitMap.get(Category.SOCKS);
            String photo = c.getPhoto();
            File file = new File(photo);
            if (photo != null && !photo.equals("") && file.exists()) {
                socksImageView.setImageURI(android.net.Uri.parse(photo));
            }
            socksTextView.setText(c.getName());
        }
        if (outfitMap.containsKey(Category.PANTS)) {
            Clothing c = outfitMap.get(Category.PANTS);
            String photo = c.getPhoto();
            File file = new File(photo);
            if (photo != null && !photo.equals("") && file.exists()) {
                pantsImageView.setImageURI(android.net.Uri.parse(photo));
            }
            pantsTextView.setText(c.getName());
        }

        nameView = (EditText)findViewById(R.id.outfit_generated_view_edit_name);
        nameView.setText(outfit.getName(), TextView.BufferType.EDITABLE);

        nameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Log.d("ClothingEditView", "Editor Action Done");
                    editOutfitName(v.getText().toString());
                    // Hide Keyboard
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(nameView.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    private void editOutfitName(String name) {
        Log.d("OutfitGeneratedView", String.format("Changing outfit %s to %s", outfit.getName(), name));
        this.outfit.setName(name);
    }
}
