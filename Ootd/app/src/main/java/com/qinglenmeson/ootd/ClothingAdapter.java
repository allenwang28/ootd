package com.qinglenmeson.ootd;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public class ClothingAdapter extends PagerAdapter {

    private Context context;

    private ArrayList<Clothing> closet;

    public ClothingAdapter(Context context) {
        this.context = context;

        // Below is just for testing/prototyping
        closet = new ArrayList<>();
        closet.add(new Clothing("Blouse", Category.BLOUSE, Warmth.WARM, Occasion.ATHLETIC));
        closet.add(new Clothing("Shorts", Category.PANTS, Warmth.WARM, Occasion.ATHLETIC));
        closet.add(new Clothing("Pants", Category.PANTS, Warmth.WARM, Occasion.ATHLETIC));
        closet.add(new Clothing("Jacket", Category.BLOUSE, Warmth.WARM, Occasion.ATHLETIC));
        closet.add(new Clothing("Jeans", Category.BLOUSE, Warmth.WARM, Occasion.ATHLETIC));
    }

    @Override
    public int getCount() {
        return closet.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ClothingEditView clothingView = new ClothingEditView(context, closet.get(position));
//        clothingView.setClothing(closet.get(position));
        ((ViewPager)container).addView(clothingView);
        return clothingView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((ClothingEditView)object);
    }
}
