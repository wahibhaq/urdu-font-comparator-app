package projects.hobby.urdufontcomparator.mvp;

import android.graphics.Typeface;

/**
 * Created by Zeeshan on 4/12/17.
 */

public interface ContentMvp {
    interface View {
        void setConvertedText(Typeface typeface);

        void setFontSize(int size);

        void setSampleText(String sampleText);
    }

    interface Presenter {
        void handleFontSelection(String fontName);

        void handleFontSize(int size);

        void handleSampleTextShowing();
    }
}
