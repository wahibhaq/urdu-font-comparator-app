package com.androidistan.urdufontcomparator.models;

import android.content.Context;

import javax.inject.Inject;

import com.androidistan.urdufontcomparator.R;

/**
 * Acts as a Model for Content as for now it serves for Sample Text and Dialog for Overflow Menu Items
 *
 * I know it could have been done in a better way but going with an easy solution for now.
 * //TODO Extract the Dialog part and introduce a Manager with a generic Fragment to handle this.
 */
public class UrduTextSource {

    private Context context;

    @Inject
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
                .concat(context.getString(R.string.urdu_sample_text_poetry_4))
                .concat(getLineSpacingsWithDash())
                .concat(context.getString(R.string.urdu_sample_text_poetry_5));

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

    public String prepareFontInfoDialogText(UrduFont font) {
        return context.getString(R.string.dialog_font_provider_label,
                        font.getProvider() != null ? font.getProvider() : R.string.not_available)
                .concat(getLineSpacings())
                .concat(context.getString(R.string.dialog_font_website_label,
                        font.getWebsite() != null ? font.getWebsite() : R.string.not_available))
                .concat(getLineSpacings())
                .concat(context.getString(R.string.dialog_font_filename_label, font.getFilename()))
                .concat(getLineSpacings())
                .concat(context.getString(R.string.dialog_font_size_label, font.getFilesize()));
    }

    public String prepareAboutUsDialogText() {
        return  getLineSpacings()
                .concat(context.getString(R.string.project_description))
                .concat(getLineSpacingsWithDash())
                .concat(getLineSpacings())
                .concat(context.getString(R.string.project_blog_page_mention,
                        context.getString(R.string.app_blog_page)))
                .concat(getLineSpacings())
                .concat(getLineSpacings())
                .concat(context.getString(R.string.project_github_comment,
                        context.getString(R.string.app_github_repo_url)));
    }

    public String prepareCreditsDialogText() {
        return getLineSpacings()
                .concat(context.getString(R.string.credits_text,
                        context.getString(R.string.app_github_credits_url)));
    }

    private String getLineSpacings() {
        return context.getString(R.string.line_spacing);
    }
}
