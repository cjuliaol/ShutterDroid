package com.example.thewizard.cjuliaol.shutterdroid.api;

import android.provider.MediaStore;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cjuliaol on 10/07/2015.
 */
public class Image {

    @SerializedName("id")
    private String mId;

    public String getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getLargeThumbnail() {
        return mAssets.mLargeThumb.mUrl;
    }

    @SerializedName("description")
    private String mDescription;

    @SerializedName("assets")
    private ImageAssets mAssets;

    private class ImageAssets{
      @SerializedName("small_thumb")
       private Thumbnail mSmallThumb;

        @SerializedName("large_thumb")
        private Thumbnail mLargeThumb;


        @SerializedName("preview")
        private Thumbnail mPreview;
    }

    private class Thumbnail {
        @SerializedName("url")
        private String mUrl;
    }

}
