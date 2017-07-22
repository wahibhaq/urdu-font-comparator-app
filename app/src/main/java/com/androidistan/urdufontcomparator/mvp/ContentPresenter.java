package com.androidistan.urdufontcomparator.mvp;

import android.graphics.Typeface;
import com.androidistan.urdufontcomparator.models.UrduTextSource;
import com.androidistan.urdufontcomparator.tracking.TrackingManager;
import com.androidistan.urdufontcomparator.utils.CustomFontManager;

public class ContentPresenter implements ContentMvp.Presenter {

    private static final String FONTS = "fonts/";

    private ContentMvp.View view;

    private CustomFontManager customFontManager;

    private UrduTextSource urduTextSource;

    private TrackingManager tracker;

    public ContentPresenter(ContentMvp.View view, CustomFontManager customFontManager,
                            UrduTextSource urduTextSource, TrackingManager trackingManager) {
        this.view = view;
        this.customFontManager = customFontManager;
        this.urduTextSource = urduTextSource;
        this.tracker = trackingManager;
    }

    @Override
    public void handleFontSelection(String fontName, String fontFileName) {
        tracker.pickFont(fontName);
        String fontAsset = getFontAsset(fontFileName);
        Typeface typeface = customFontManager.getFont(fontAsset);
        view.setConvertedText(typeface);
    }

    @Override
    public void handleSampleTextShowing() {
        view.setSampleText(urduTextSource.prepareSampleText());
    }

    private String getFontAsset(String fileName) {
        return FONTS.concat(fileName);
    }
}
