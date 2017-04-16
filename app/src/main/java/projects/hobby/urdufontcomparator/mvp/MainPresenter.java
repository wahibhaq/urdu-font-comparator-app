package projects.hobby.urdufontcomparator.mvp;

import android.util.Log;
import java.util.List;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.data.FontApi;
import projects.hobby.urdufontcomparator.models.UrduFont;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.utils.CustomFontManager;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainPresenter implements MainMvp.Presenter {

    private static final String FONTS = "fonts/";

    private final MainMvp.View view;

    private final CustomFontManager fontManager;

    private final UrduTextSource urduTextSource;

    private final FontApi fontSource;

    public MainPresenter(MainMvp.View view, CustomFontManager fontManager,
            UrduTextSource urduTextSource, FontApi fontSource) {
        this.view = view;
        this.fontManager = fontManager;
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
                            view.setConvertedText(fontManager.getFont(fontAsset));
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
            view.showFontInfoDialog(font, urduTextSource.prepareFontInfoDialogText(font));
        }
    }

    private String getFontAsset(String fileName) {
        return FONTS.concat(fileName);
    }

    private void handleError(Throwable throwable) {
        Log.e(getClass().getSimpleName(), throwable.getMessage());
        view.showError(R.string.error_message_generic);
    }
}
