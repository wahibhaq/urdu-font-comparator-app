package com.androidistan.urdufontcomparator.mvp;

import android.graphics.Typeface;


/**
 * Creates contract between view and presenter
 */
public interface ContentMvp {

    interface View {

        void setConvertedText(Typeface typeface);

        void setSampleText(String sampleText);

    }

    interface Presenter {

        void handleFontSelection(String fontName, String fontFileName);

        void handleSampleTextShowing();

    }
}
