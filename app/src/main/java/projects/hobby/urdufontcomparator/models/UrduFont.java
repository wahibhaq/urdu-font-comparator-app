package projects.hobby.urdufontcomparator.models;

import com.google.gson.annotations.SerializedName;

public class UrduFont {
    //TODO later add download link, source, license, provider info etc

    @SerializedName("name")
    private String fontName;

    @SerializedName("provider")
    private final String provider;

    @SerializedName("website")
    private final String website;

    @SerializedName("download_url")
    private final String downloadLink;

    public UrduFont(String provider, String website, String downloadLink, String fontName) {
        this.provider = provider;
        this.website = website;
        this.downloadLink = downloadLink;
        this.fontName = fontName;
    }

    public String getFontName() {
        return fontName;
    }

    public String getProvider() {
        return provider;
    }

    public String getWebsite() {
        return website;
    }

    public String getDownloadLink() {
        return downloadLink;
    }
}
