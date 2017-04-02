package projects.hobby.urdufontcomparator.mvp;

import android.graphics.Typeface;
import java.util.List;
import projects.hobby.urdufontcomparator.models.UrduFonts;

/**
 * Specifies the contract between the view and the presenter
 */
public interface MainMvp {

    interface View {
        void setFontSelectorContent(List<String> fontNames);

        void setConvertedText(Typeface typeface);

        void showFontInfoDialog(UrduFonts font, String content);

        void showProgress(boolean show);

        void showError(int errorMessageId);

        void showAndSetSeekbar(boolean show);

        void setFontSize(int size);

        void setSampleText(String sampleText);
    }

    interface Presenter {
        void loadFontsAvailable();

        void handleFontSelection(String fontName);

        void handleFontInfoAction(String fontFile);

        void handleFontSize(int size);

        void handleSampleTextShowing();
    }
}
