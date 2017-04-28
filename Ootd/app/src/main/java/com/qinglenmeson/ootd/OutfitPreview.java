package com.qinglenmeson.ootd;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen Wang at 4/27/2017
 */
public class OutfitPreview extends LinearLayout {
    private Outfit outfit;
    private Context context;
    private ImageView tshirtImageView;
    private ImageView shirtImageView;
    private ImageView shoesImageView;
    private ImageView jacketImageView;
    private ImageView sweaterImageView;
    private ImageView socksImageView;
    private ImageView pantsImageView;

    public void initializeViews(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.outfit_view, this);
        Log.d("OutfitPreview", "Initialized View");
        tshirtImageView = (ImageView)findViewById(R.id.outfitpreview_Tshirt);
        shirtImageView = (ImageView)findViewById(R.id.outfitpreview_Shirt);
        shoesImageView = (ImageView)findViewById(R.id.outfitpreview_Shoes);
        jacketImageView = (ImageView)findViewById(R.id.outfitpreview_Jacket);
        sweaterImageView = (ImageView)findViewById(R.id.outfitpreview_Sweater);
        socksImageView = (ImageView)findViewById(R.id.outfitpreview_Socks);
        pantsImageView = (ImageView)findViewById(R.id.outfitpreview_Pants);
        setStockViews();
    }

    public OutfitPreview(Context context) {
        super(context);
        initializeViews(context);
    }

    public OutfitPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public OutfitPreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }


    public OutfitPreview(Context context, Outfit outfit) {
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
    }

    private void setView() {
        Map<Category, Clothing> outfitMap = outfit.getClothingMap();
        setStockViews();

        if (outfitMap.containsKey(Category.TSHIRT)) {
            String photo = outfitMap.get(Category.TSHIRT).getPhoto();
            if (photo != null) {
                tshirtImageView.setImageURI(android.net.Uri.parse(photo));
            }
        }
        if (outfitMap.containsKey(Category.SHIRT)) {
            String photo = outfitMap.get(Category.SHIRT).getPhoto();
            if (photo != null) {
                shirtImageView.setImageURI(android.net.Uri.parse(photo));
            }
        }
        if (outfitMap.containsKey(Category.SHOES)) {
            String photo = outfitMap.get(Category.SHOES).getPhoto();
            if (photo != null) {
                shoesImageView.setImageURI(android.net.Uri.parse(photo));
            }
        }
        if (outfitMap.containsKey(Category.JACKET)) {
            String photo = outfitMap.get(Category.JACKET).getPhoto();
            if (photo != null) {
                jacketImageView.setImageURI(android.net.Uri.parse(photo));
            }
        }
        if (outfitMap.containsKey(Category.SWEATER)) {
            String photo = outfitMap.get(Category.SWEATER).getPhoto();
            if (photo != null) {
                sweaterImageView.setImageURI(android.net.Uri.parse(photo));
            }
        }
        if (outfitMap.containsKey(Category.SOCKS)) {
            String photo = outfitMap.get(Category.SOCKS).getPhoto();
            if (photo != null) {
                socksImageView.setImageURI(android.net.Uri.parse(photo));
            }
        }
        if (outfitMap.containsKey(Category.PANTS)) {
            String photo = outfitMap.get(Category.PANTS).getPhoto();
            if (photo != null) {
                pantsImageView.setImageURI(android.net.Uri.parse(photo));
            }
        }
    }
}
