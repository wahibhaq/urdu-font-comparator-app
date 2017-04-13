package projects.hobby.urdufontcomparator.mvp;

import android.graphics.Typeface;

import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.utils.CustomFontManager;

/**
 * Created by Zeeshan on 4/12/17.
 */

public class ContentPresenter implements ContentMvp.Presenter {
    private static final String FONTS = "fonts/";
    ContentMvp.View view;
    CustomFontManager customFontManager;
    UrduTextSource urduTextSource;


    public ContentPresenter(ContentMvp.View view, CustomFontManager customFontManager, UrduTextSource
            urduTextSource) {
        this.view = view;
        this.customFontManager = customFontManager;
        this.urduTextSource = urduTextSource;
    }

    @Override
    public void handleFontSelection(String fontName) {
        String fontAsset = getFontAsset(fontName);
        Typeface typeface = customFontManager.getFont(fontAsset);
        view.setConvertedText(typeface);
    }

    @Override
    public void handleFontSize(int size) {
        view.setFontSize(size);
    }

    @Override
    public void handleSampleTextShowing() {
        view.setSampleText(urduTextSource.prepareSampleText());
    }

    private String getFontAsset(String fileName) {
        return FONTS.concat(fileName);
    }
}