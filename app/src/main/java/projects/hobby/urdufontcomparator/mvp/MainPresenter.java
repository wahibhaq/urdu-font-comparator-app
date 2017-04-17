package projects.hobby.urdufontcomparator.mvp;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.models.UrduFontsSource;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.utils.CustomFontManager;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainPresenter implements MainMvp.Presenter {

    private static final String FONTS = "fonts/";

    private static final int PROGRESS_DIALOG_FAKE_DELAY = 1; //1 second

    private final MainMvp.View view;

    private final CustomFontManager fontManager;

    private final UrduTextSource urduTextSource;

    public MainPresenter(MainMvp.View view, CustomFontManager fontManager,
            UrduTextSource urduTextSource) {
        this.view = view;
        this.fontManager = fontManager;
        this.urduTextSource = urduTextSource;
    }

    @Override
    public void loadFontsAvailable() {
        final List<String> fontList = new ArrayList<>(Arrays.asList(UrduFontsSource.getFontNames()));
        view.setFontSelectorContent(fontList);
    }

    @Override
    public void handleFontSelection(String fontFileName) {
        view.showProgress(true);
        Observable.just(getFontAsset(fontFileName))
                .delay(PROGRESS_DIALOG_FAKE_DELAY, TimeUnit.SECONDS,
                        AndroidSchedulers.mainThread())
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

    @Override
    public void handleFontInfoAction(String font) {
        final UrduFontsSource selectedFont = UrduFontsSource.from(font);
        view.showFontInfoDialog(selectedFont,
                urduTextSource.prepareFontInfoDialogText(selectedFont));
    }

    private String getFontAsset(String fileName) {
        return FONTS.concat(fileName);
    }

    private void handleError(Throwable throwable) {
        Log.e(getClass().getSimpleName(), throwable.getMessage());
        view.showError(R.string.error_message);
    }
}
