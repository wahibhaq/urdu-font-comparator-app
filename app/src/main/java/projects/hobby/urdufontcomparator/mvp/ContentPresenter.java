package projects.hobby.urdufontcomparator.mvp;

import android.graphics.Typeface;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.tracking.TrackingManager;
import projects.hobby.urdufontcomparator.utils.CustomFontManager;

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
