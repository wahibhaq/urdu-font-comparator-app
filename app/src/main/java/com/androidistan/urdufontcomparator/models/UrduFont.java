package com.androidistan.urdufontcomparator.models;

import android.support.annotation.Nullable;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

@IgnoreExtraProperties
public class UrduFont {

    @SerializedName("name")
    private String name;

    @SerializedName("filename")
    private String filename;

    @Nullable
    @SerializedName("provider")
    private String provider;

    @Nullable
    @SerializedName("website")
    private String website;

    @SerializedName("filesize")
    private String filesize;

    @SerializedName("ratingCount")
    private int ratingCount;

    @SerializedName("ratingSum")
    private int ratingSum;

    @SerializedName("lastRatingValue")
    private int lastRatingValue;

    public UrduFont() {
        // Default constructor required for calls to DataSnapshot.getValue(UrduFont.class)
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public @Nullable String getProvider() {
        return provider;
    }

    public @Nullable String getWebsite() {
        return website;
    }

    public String getFilesize() {
        return filesize;
    }

    // I have found out that these getter functions are important to be present
    // so that values can be updated on server
    public int getRatingCount() {
        return ratingCount;
    }

    public int getRatingSum() {
        return ratingSum;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void setRatingSum(int ratingSum) {
        this.ratingSum = ratingSum;
    }

    public void setLastRatingValue(int lastRatingValue) {
        this.lastRatingValue = lastRatingValue;
    }

    public int getLastRatingValue() {
        return lastRatingValue;
    }

    public void incrementRatingCount() {
        setRatingCount(getRatingCount() + 1);
    }

    public void updateRatingSum(int ratingSum) {
        setLastRatingValue(ratingSum);
        setRatingSum(getRatingSum() + ratingSum);
    }

}
