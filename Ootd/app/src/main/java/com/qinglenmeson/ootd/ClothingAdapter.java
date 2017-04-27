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
import java.util.List;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public class ClothingAdapter extends PagerAdapter {

    private Context context;

    private List<Clothing> closet;

    public ClothingAdapter(Context context, List<Clothing> closet) {
        this.context = context;
        this.closet = closet;
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
        ((ViewPager)container).addView(clothingView);
        return clothingView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((ClothingEditView)object);
    }
}
