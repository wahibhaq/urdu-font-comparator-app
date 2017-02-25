package projects.hobby.urdufontcomparator.mvp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.data.FontApi;
import projects.hobby.urdufontcomparator.models.UrduFont;
import projects.hobby.urdufontcomparator.models.UrduFontsSource;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.utils.CustomFontManager;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainPresenter implements MainMvp.Presenter {

    private static final String FONTS = "fonts/";

    private static final int PROGRESS_DIALOG_FAKE_DELAY = 1; //1 second

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
        //final List<String> fontList = new ArrayList<>(Arrays.asList(UrduFontsSource.getFontNames()));
        //view.setFontSelectorContent(fontList);
        fontSource.getFonts()
                .map(new Func1<List<UrduFont>, List<String>>() {
                    @Override
                    public List<String> call(List<UrduFont> urduFonts) {
                        Log.i("UrduApp", "Size of List of urdufonts: " + urduFonts.size());
                        List<String> fontNames = new ArrayList<>();
                        for(UrduFont font: urduFonts) {
                            fontNames.add(font.getFontName());
                        }
                        return fontNames;
                    }
                })
                .doOnNext(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> fontNames) {
                        view.setFontSelectorContent(fontNames);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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
