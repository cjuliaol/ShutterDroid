package com.example.thewizard.cjuliaol.shutterdroid.api;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.thewizard.cjuliaol.shutterdroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by cjuliaol on 13/07/2015.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    private static final String TAG = "ImagesAdapter";
    private List<Image> mImages;
    private Context mContext;

    public ImagesAdapter(Context context,List<Image> images) {
        this.mImages = images;
        this.mContext= context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_image,parent,false);

        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image image = mImages.get(position);
        Log.d(TAG,"Image Description: " +image.getDescription());
        Log.d(TAG,"Image Url: " +image.getLargeThumbnail());
        Picasso.with(mContext).load(image.getLargeThumbnail()).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }



}
