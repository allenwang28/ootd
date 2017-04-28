package com.qinglenmeson.ootd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Allen Wang on 3/23/2017.
 */

public class ClothingPreviewAdapter extends RecyclerView.Adapter<ClothingPreviewAdapter.ViewHolder>{

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public ImageView imageView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.clothingpreview_Name);
            imageView = (ImageView) itemView.findViewById(R.id.clothingpreview_Image);
        }
    }

    // List of all clothes
    private List<Clothing> mClothingList;

    // Store context for easy access
    private Context mContext;

    // Adapter constructor
    public ClothingPreviewAdapter(Context context, List<Clothing> clothingList) {
        this.mContext = context;
        this.mClothingList = clothingList;
    }

    // Allows easy access to context for the RecyclerView
    private Context getContext() {
        return mContext;
    }

    @Override
    public ClothingPreviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View clothingPreview = inflater.inflate(R.layout.clothing_preview_view, parent, false);

        // return a new holder instance
        ViewHolder viewHolder = new ViewHolder(clothingPreview);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ClothingPreviewAdapter.ViewHolder holder, int position) {
        // Get data model based on position
        Clothing clothing = mClothingList.get(position);

        // Set item views based on views and data model...
        TextView view = holder.nameTextView;
        view.setText(clothing.getName());

        ImageView imageView = holder.imageView;
        String photo = clothing.getPhoto();
        if(photo != null) {
            System.out.println(photo);
            imageView.setImageURI(null);
            imageView.setImageURI(android.net.Uri.parse(photo));
        } else {
            imageView.setImageResource(R.drawable.blue_jeans);
        }
    }

    @Override
    public int getItemCount() {
        return mClothingList.size();
    }
}
