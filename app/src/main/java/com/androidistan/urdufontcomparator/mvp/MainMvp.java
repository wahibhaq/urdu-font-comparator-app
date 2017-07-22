package com.androidistan.urdufontcomparator.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.androidistan.urdufontcomparator.models.UrduFont;

import java.util.List;

/**
 * Specifies the contract between the view and the presenter
 */
public interface MainMvp {

    interface View {

        void setFontSelectorContent(@NonNull List<UrduFont> fonts);

        void showFontDetailsDialog(UrduFont font, double rating, int ratingCount);

        void showProgress();

        void hideProgress();

        void showError(@StringRes int errorMessageId);

        void showFontRatingDialog(UrduFont font);

        void showToast(@StringRes int messageId);

    }

    interface Presenter {

        void dispose();

        void loadFontsAvailable();

        void handleFontInfoAction(@Nullable UrduFont font);

        void handleFontRatingShowAction(@Nullable UrduFont font);

        void handleRatingUpdateAction(int fontIndex, UrduFont font);

    }
}
