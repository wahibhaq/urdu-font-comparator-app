package projects.hobby.urdufontcomparator.mvp;

import android.util.Log;
import java.util.List;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.data.FontApi;
import projects.hobby.urdufontcomparator.models.UrduFont;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainPresenter implements MainMvp.Presenter {

    private static final String FONTS = "fonts/";

    private final MainMvp.View view;

    private final UrduTextSource urduTextSource;

    private final FontApi fontSource;

    public MainPresenter(MainMvp.View view,
            UrduTextSource urduTextSource, FontApi fontSource) {
        this.view = view;
        this.urduTextSource = urduTextSource;
        this.fontSource = fontSource;

    }

    @Override
    public void loadFontsAvailable() {
        view.showProgress(true);
        fontSource.getFonts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<List<UrduFont>>() {
                    @Override public void call(List<UrduFont> fonts) {
                        view.showProgress(false);
                        view.setFontSelectorContent(fonts);
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        view.showProgress(false);
                        handleError(throwable);
                    }
                });
    }

    @Override
    public void handleFontSelection(String fontFileName) {
        if(fontFileName.isEmpty()) {
            view.showError(R.string.error_message_unknown_font);
        } else {
            view.showProgress(true);
            Observable.just(getFontAsset(fontFileName))
                    .subscribe(new Action1<String>() {
                        @Override public void call(String fontAsset) {
                            view.showProgress(false);
                            view.showAndSetSeekbar(true);
                        }
                    }, new Action1<Throwable>() {
                        @Override public void call(Throwable throwable) {
                            view.showProgress(false);
                            view.showAndSetSeekbar(false);
                            handleError(throwable);
                        }
                    });
        }
    }

    @Override
    public void handleFontInfoAction(UrduFont font) {
        if(font == null) {
            view.showError(R.string.error_message_unknown_font);
        } else {
            view.showFontDetailsDialog(font, urduTextSource.prepareFontInfoDialogText(font));
        }
    }

    @Override
    public void handleFontRateAction(UrduFont font) {
        if(font == null) {
            view.showError(R.string.error_message_unknown_font);
        } else {
            view.showFontRatingDialog(font);
        }
    }

    private String getFontAsset(String fileName) {
        return FONTS.concat(fileName);
    }

    private void handleError(Throwable throwable) {
        Timber.e(getClass().getSimpleName(), throwable.getMessage());
        view.showError(R.string.error_message_generic);
    }
}
