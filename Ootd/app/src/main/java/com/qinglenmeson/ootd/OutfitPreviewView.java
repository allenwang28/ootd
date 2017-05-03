package com.qinglenmeson.ootd;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.Map;

/**
 * Created by Allen Wang on 5/2/2017.
 */

public class OutfitPreviewView extends LinearLayout {
    private Context context;
    private Outfit outfit;
    private ImageView top;
    private ImageView mid;
    private ImageView low;
    private TextView name;

    public OutfitPreviewView(Context context) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.outfit_preview_view, this);
        Log.d("OutfitPreviewView", "Initialized View");
    }

    public OutfitPreviewView(Context context, Outfit o) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.outfit_preview_view, this);
        initializeView(o, context);
    }

    public OutfitPreviewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.outfit_preview_view, this);
    }

    public OutfitPreviewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.outfit_preview_view, this);
    }


    public void setOutfit(Outfit o) {
        outfit = o;
        initializeView(o, this.context);
    }

    public Outfit getOutfit() {
        return outfit;
    }

    public void setImageViewFromClothing(ImageView imageView, Clothing clothing, Category category) {
        if (clothing != null) {
            String photo = clothing.getPhoto();
            File file = new File(photo);
            if (photo != null && !photo.equals("") && file.exists()) {
                System.out.println(photo);
                imageView.setImageURI(android.net.Uri.parse(photo));
                return;
            }
        }
        switch (category) {
            case SHIRT:
                imageView.setImageResource(R.drawable.shirt);
                break;
            case SWEATER:
                imageView.setImageResource(R.drawable.sweater);
                break;
            case PANTS:
                imageView.setImageResource(R.drawable.pants);
                break;
            case JACKET:
                imageView.setImageResource(R.drawable.jacket);
                break;
            case SOCKS:
                imageView.setImageResource(R.drawable.socks);
                break;
            case SHOES:
                imageView.setImageResource(R.drawable.shoes);
                break;
            case TSHIRT:
                imageView.setImageResource(R.drawable.tshirt);
                break;
            default:
                imageView.setImageResource(R.drawable.tshirt);
        }
    }

    public void initializeView(Outfit outfit, Context context) {
        this.outfit = outfit;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.outfit_preview_view, this);
        Log.d("OutfitPreviewView", "Initialized View");
        top = (ImageView)findViewById(R.id.outfitpreview_top);
        mid = (ImageView)findViewById(R.id.outfitpreview_mid);
        low = (ImageView)findViewById(R.id.outfitpreview_low);
        name = (TextView)findViewById(R.id.outfitpreview_name);

        Map<Category, Clothing> outfitMap = outfit.getClothingMap();
        // Set item views based on views and data model...

        // Top imageview would have either a jacket or a tshirt or shirt
        if (outfitMap.containsKey(Category.JACKET)) {
            setImageViewFromClothing(top, outfitMap.get(Category.JACKET), Category.JACKET);
        } else if (outfitMap.containsKey(Category.SHIRT)) {
            setImageViewFromClothing(top, outfitMap.get(Category.JACKET), Category.SHIRT);
        } else if (outfitMap.containsKey(Category.TSHIRT)) {
            setImageViewFromClothing(top, outfitMap.get(Category.JACKET), Category.TSHIRT);
        } else {
            setImageViewFromClothing(top, null, Category.TSHIRT);
        }

        // Middle imageview would have pants
        if (outfitMap.containsKey(Category.PANTS)) {
            setImageViewFromClothing(mid, outfitMap.get(Category.PANTS), Category.PANTS);
        } else {
            setImageViewFromClothing(mid, null, Category.PANTS);
        }

        // Bottom imageview would have shoes
        if (outfitMap.containsKey(Category.SHOES)) {
            setImageViewFromClothing(low, outfitMap.get(Category.SHOES), Category.SHOES);
        } else {
            setImageViewFromClothing(low, null, Category.SHOES);
        }

        name.setText(outfit.getName());
    }
}
