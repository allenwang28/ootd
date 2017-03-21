package com.qinglenmeson.ootd;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
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
        TextView textView = new TextView(context);
        textView.setPadding(5, 5, 5, 5);
        textView.setBackgroundColor(Color.BLACK);
        textView.setTextColor(Color.WHITE);

        textView.setText(closet.get(position).getName());
        ((ViewPager)container).addView(textView);
        return textView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((TextView)object);
    }
}
