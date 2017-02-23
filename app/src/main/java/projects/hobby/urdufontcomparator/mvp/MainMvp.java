package projects.hobby.urdufontcomparator.mvp;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import projects.hobby.urdufontcomparator.models.UrduFonts;

/**
 * Specifies the contract between the view and the presenter
 */
public interface MainMvp {

    interface View {

        void showFontSelector();

        void showSampleText();

        void setConvertedText(Typeface typeface);

        void showFontInfoDialog(UrduFonts font);

        void showAboutMeInfo();

        void showLicenseInfo();

    }

    interface Presenter {

        void handleFontSelection(String fontName);

        void handleFontInfoAction(String fontFile);

    }
}
