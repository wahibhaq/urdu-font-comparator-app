package projects.hobby.urdufontcomparator.mvp;

import projects.hobby.urdufontcomparator.models.UrduFonts;
import projects.hobby.urdufontcomparator.utils.CustomFontManager;

public class MainPresenter implements MainMvp.Presenter {

    private static final String FONTS = "fonts/";

    private final MainMvp.View view;

    private final CustomFontManager fontManager;

    public MainPresenter(MainMvp.View view, CustomFontManager fontManager) {
        this.view = view;
        this.fontManager = fontManager;
    }

    @Override
    public void handleFontSelection(String fontFileName) {
        final String fontAsset = getFontAsset(fontFileName);
        view.setConvertedText(fontManager.getFont(fontAsset));
    }

    @Override
    public void handleFontInfoAction(String font) {
        view.showFontInfoDialog(UrduFonts.from(font));
    }

    private String getFontAsset(String fileName) {
        return FONTS.concat(fileName);
    }
}
