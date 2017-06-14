package projects.hobby.urdufontcomparator.models;


import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Contributors {

    @SerializedName("name")
    private String name;

    @SerializedName("website")
    @Nullable
    private String website;

    public Contributors() {
        // Default constructor required for calls to DataSnapshot.getValue(Contributors.class)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getWebsite() {
        return website;
    }

    public void setWebsite(@Nullable String website) {
        this.website = website;
    }
}
