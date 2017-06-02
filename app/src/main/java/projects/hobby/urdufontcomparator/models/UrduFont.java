package projects.hobby.urdufontcomparator.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

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

    public UrduFont() {

    }

    public UrduFont(String name, String filename, String provider, String website,
                    String filesize, int ratingCount, int ratingSum) {
        this.name = name;
        this.filename = filename;
        this.provider = provider;
        this.website = website;
        this.filesize = filesize;
        this.ratingCount = ratingCount;
        this.ratingSum = ratingSum;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public String getProvider() {
        return provider;
    }

    public String getWebsite() {
        return website;
    }

    public String getFilesize() {
        return filesize;
    }

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
}
