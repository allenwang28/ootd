package com.qinglenmeson.ootd;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Allen Wang on 3/21/2017.
 */

public class OutfitAdapter extends PagerAdapter {

    private Context context;
    Closet closet;

    public OutfitAdapter(Context context) {
        this.context = context;
        closet = Closet.getInstance();
    }

    @Override
    public int getCount() {
        return closet.outfitListSize();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        OutfitEditView outfitView = new OutfitEditView(context, closet.getOutfitList().get(position));
        ((ViewPager)container).addView(outfitView);
        return outfitView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((OutfitEditView)object);
    }
}
