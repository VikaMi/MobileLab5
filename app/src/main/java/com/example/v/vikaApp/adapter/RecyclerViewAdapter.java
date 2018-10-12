package com.example.v.vikaApp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.v.vikaApp.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private String TAG = "Adapter";

    private ArrayList<String> mImageNames;
    private ArrayList<Bitmap> mImages;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<Bitmap> images) {
        Log.i(TAG, "Constructor");
        mImages = images;
        mContext = context;
        Log.i(TAG, "ImageNames size: " + mImages.size());
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.i(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_listitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.i(TAG, "onBindViewHolder");
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(viewHolder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        RelativeLayout parentLayout;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
