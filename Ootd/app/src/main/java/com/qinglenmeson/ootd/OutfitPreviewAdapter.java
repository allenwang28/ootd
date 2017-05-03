package com.qinglenmeson.ootd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen Wang on 3/23/2017.
 */

public class OutfitPreviewAdapter extends RecyclerView.Adapter<OutfitPreviewAdapter.ViewHolder>{

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView imageViewTop;
        public ImageView imageViewMid;
        public ImageView imageViewLow;
        public TextView nameView;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            imageViewTop = (ImageView) itemView.findViewById(R.id.outfitpreview_top);
            imageViewMid = (ImageView) itemView.findViewById(R.id.outfitpreview_mid);
            imageViewLow = (ImageView) itemView.findViewById(R.id.outfitpreview_low);
            nameView = (TextView) itemView.findViewById(R.id.outfitpreview_name);
        }
    }

    // Store context for easy access
    private Context mContext;

    // Adapter constructor
    public OutfitPreviewAdapter(Context context) {
        this.mContext = context;
    }

    // Allows easy access to context for the RecyclerView
    private Context getContext() {
        return mContext;
    }

    @Override
    public OutfitPreviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View outfitPreview = inflater.inflate(R.layout.outfit_preview_view, parent, false);

        // return a new holder instance
        ViewHolder viewHolder = new ViewHolder(outfitPreview);
        return viewHolder;
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

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(OutfitPreviewAdapter.ViewHolder holder, int position) {
        // Get data model based on position
        Closet closet = Closet.getInstance();
        List<Outfit> outfitList = closet.getOutfitList();
        Outfit outfit = outfitList.get(position);
        Map<Category, Clothing> outfitMap = outfit.getClothingMap();
        // Set item views based on views and data model...

        // Top imageview would have either a jacket or a tshirt or shirt
        if (outfitMap.containsKey(Category.JACKET)) {
            setImageViewFromClothing(holder.imageViewTop, outfitMap.get(Category.JACKET), Category.JACKET);
        } else if (outfitMap.containsKey(Category.SHIRT)) {
            setImageViewFromClothing(holder.imageViewTop, outfitMap.get(Category.JACKET), Category.SHIRT);
        } else if (outfitMap.containsKey(Category.TSHIRT)) {
            setImageViewFromClothing(holder.imageViewTop, outfitMap.get(Category.JACKET), Category.TSHIRT);
        } else {
            setImageViewFromClothing(holder.imageViewTop, null, Category.TSHIRT);
        }

        // Middle imageview would have pants
        if (outfitMap.containsKey(Category.PANTS)) {
            setImageViewFromClothing(holder.imageViewMid, outfitMap.get(Category.PANTS), Category.PANTS);
        } else {
            setImageViewFromClothing(holder.imageViewMid, null, Category.PANTS);
        }

        // Bottom imageview would have shoes
        if (outfitMap.containsKey(Category.SHOES)) {
            setImageViewFromClothing(holder.imageViewLow, outfitMap.get(Category.SHOES), Category.SHOES);
        } else {
            setImageViewFromClothing(holder.imageViewLow, null, Category.SHOES);
        }

        holder.nameView.setText(outfit.getName());
    }

    @Override
    public int getItemCount() {
        Closet closet = Closet.getInstance();
        return closet.outfitListSize();
    }
}
