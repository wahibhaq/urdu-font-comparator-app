package com.androidistan.urdufontcomparator.models;

import android.content.Context;

import com.androidistan.urdufontcomparator.R;
import com.androidistan.urdufontcomparator.utils.Utils;

import javax.inject.Inject;

/**
 * Acts as a Model for Content as for now it serves for Sample Text
 * <p>
 * I know it could have been done in a better way but going with an easy solution for now.
 */
public class UrduTextSource {

    private final Context context;

    @Inject
    public UrduTextSource(Context context) {
        this.context = context;
    }

    public String prepareSampleText() {
        String poetry = context.getString(R.string.urdu_sample_text_poetry_1)
                .concat(Utils.getLineSpacingsWithDash(context))
                .concat(context.getString(R.string.urdu_sample_text_poetry_2))
                .concat(Utils.getLineSpacingsWithDash(context))
                .concat(context.getString(R.string.urdu_sample_text_poetry_3))
                .concat(Utils.getLineSpacingsWithDash(context))
                .concat(context.getString(R.string.urdu_sample_text_poetry_4))
                .concat(Utils.getLineSpacingsWithDash(context))
                .concat(context.getString(R.string.urdu_sample_text_poetry_5));

        String textToBold = context.getString(R.string.urdu_sample_text_bold);
        String alphabets = context.getString(R.string.urdu_sample_text_alphabets);
        return textToBold.concat(Utils.getLineSpacingsWithDash(context))
                .concat(poetry)
                .concat(Utils.getLineSpacingsWithDash(context))
                .concat(alphabets);
    }

}
