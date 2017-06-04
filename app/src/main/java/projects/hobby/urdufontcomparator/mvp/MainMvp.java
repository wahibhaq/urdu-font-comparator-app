package projects.hobby.urdufontcomparator.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import java.util.List;
import projects.hobby.urdufontcomparator.models.UrduFont;

/**
 * Specifies the contract between the view and the presenter
 */
public interface MainMvp {

    interface View {

        void setFontSelectorContent(@NonNull List<UrduFont> fonts);

        void showFontDetailsDialog(UrduFont font, String content);

        void showProgress();

        void hideProgress();

        void showError(@StringRes int errorMessageId);

        void showFontRatingDialog(UrduFont font);

        void showToast(@StringRes int messageId);

    }

    interface Presenter {

        void dispose();

        void loadFontsAvailable();

        void handleFontInfoAction(UrduFont font);

        void handleFontRatingShowAction(UrduFont font);

        void handleRatingUpdateAction(int fontIndex, UrduFont font);

    }
}
