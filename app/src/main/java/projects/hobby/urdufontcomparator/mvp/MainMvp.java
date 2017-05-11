package projects.hobby.urdufontcomparator.mvp;

import java.util.List;
import projects.hobby.urdufontcomparator.models.UrduFont;

/**
 * Specifies the contract between the view and the presenter
 */
public interface MainMvp {

    interface View {
        void setFontSelectorContent(List<UrduFont> fonts);

        void showFontInfoDialog(UrduFont font, String content);

        void showProgress(boolean show);

        void showError(int errorMessageId);

        void showAndSetSeekbar(boolean show);
    }

    interface Presenter {
        void loadFontsAvailable();

        void handleFontSelection(String fontName);

        void handleFontInfoAction(UrduFont font);

    }
}
