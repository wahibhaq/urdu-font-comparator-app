package projects.hobby.urdufontcomparator.models;

import android.content.Context;

import projects.hobby.urdufontcomparator.R;

/**
 * Acts as a Model for Content as for now it serves for Sample Text and Font Info Dialog
 */
public class UrduTextSource {

    private Context context;

    public UrduTextSource(Context context) {
        this.context = context;
    }

    public String prepareSampleText() {
        String poetry = context.getString(R.string.urdu_sample_text_poetry_1)
                .concat(getLineSpacingsWithDash())
                .concat(context.getString(R.string.urdu_sample_text_poetry_2))
                .concat(getLineSpacingsWithDash())
                .concat(context.getString(R.string.urdu_sample_text_poetry_3))
                .concat(getLineSpacingsWithDash())
                .concat(context.getString(R.string.urdu_sample_text_poetry_4));
        String textToBold = context.getString(R.string.urdu_sample_text_bold);
        String alphabets = context.getString(R.string.urdu_sample_text_alphabets);
        return textToBold.concat(getLineSpacingsWithDash())
                .concat(poetry)
                .concat(getLineSpacingsWithDash())
                .concat(alphabets);
    }

    private String getLineSpacingsWithDash() {
        return context.getString(R.string.line_spacing_with_dash);
    }

    public String prepareFontInfoDialogText(UrduFontsSource font) {
        return getLineSpacings()
                .concat(context.getString(R.string.dialog_font_provider_label, font.provider))
                .concat(getLineSpacings())
                .concat(context.getString(R.string.dialog_font_website_label,
                        context.getString(font.website)))
                .concat(getLineSpacings())
                .concat(context.getString(R.string.dialog_font_filename_label,
                        context.getString(font.fontFileName)))
                .concat(getLineSpacings())
                .concat(context.getString(R.string.dialog_font_size_label, font.fileSize));
    }

    public String prepareDevsInfoDialogText() {
        return getLineSpacings().concat(context.getString(R.string.dev_name))
                .concat(getLineSpacings())
                .concat(context.getString(R.string.dev_url));
    }

    private String getLineSpacings() {
        return context.getString(R.string.line_spacing);
    }
}
