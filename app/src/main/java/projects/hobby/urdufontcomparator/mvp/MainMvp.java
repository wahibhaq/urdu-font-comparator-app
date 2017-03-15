package projects.hobby.urdufontcomparator.mvp;

import android.graphics.Typeface;

import java.util.List;

import projects.hobby.urdufontcomparator.models.UrduFonts;

/**
 * Specifies the contract between the view and the presenter
 */
public interface MainMvp {

    interface View {
        void showFontSelector(List<String> fontNames);

        void setConvertedText(Typeface typeface);

        void showFontInfoDialog(UrduFonts font);

        void showProgress(boolean show);

        void showError(int errorMessageId);

        void showSeekbar(boolean show);

        void setFontSize(int size);
    }

    interface Presenter {
        void loadFontsAvailable();

        void handleFontSelection(String fontName);

        void handleFontInfoAction(String fontFile);

        void handleFontSize(int size);
    }
}
