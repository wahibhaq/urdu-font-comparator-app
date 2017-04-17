package projects.hobby.urdufontcomparator.mvp;

import android.graphics.Typeface;
import java.util.List;
import projects.hobby.urdufontcomparator.models.UrduFontsSource;

/**
 * Specifies the contract between the view and the presenter
 */
public interface MainMvp {

    interface View {
        void setFontSelectorContent(List<String> fontNames);

        void showFontInfoDialog(UrduFontsSource font, String content);

        void showProgress(boolean show);

        void showError(int errorMessageId);

        void showAndSetSeekbar(boolean show);
    }

    interface Presenter {
        void loadFontsAvailable();

        void handleFontSelection(String fontName);

        void handleFontInfoAction(String fontFile);

    }
}
