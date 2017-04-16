package projects.hobby.urdufontcomparator.models;

import com.google.gson.annotations.SerializedName;

public class UrduFont {

    @SerializedName("name")
    private String fontName;

    @SerializedName("filename")
    private String filename;

    @SerializedName("provider")
    private final String provider;

    @SerializedName("website")
    private final String website;

    @SerializedName("filesize")
    private final String filesize;

    public UrduFont(String fontName, String filename, String provider, String website,
            String filesize) {
        this.fontName = fontName;
        this.filename = filename;
        this.provider = provider;
        this.website = website;
        this.filesize = filesize;
    }

    public String getFontName() {
        return fontName;
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
}
