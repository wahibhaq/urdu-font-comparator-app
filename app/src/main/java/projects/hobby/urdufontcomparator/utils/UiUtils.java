package projects.hobby.urdufontcomparator.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Patterns;
import android.widget.TextView;

import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import projects.hobby.urdufontcomparator.R;

/**
 * General Utils Class for common general-purpose UI functions
 */
public class UiUtils {

    public static void showDialogWithUrlsWithTitle(Context context, @StringRes int title,
            String content) {
        showDialogWithUrlsInContent(context, context.getString(title), content);
    }

    private static void showDialogWithUrlsInContent(Context context, String title,
            String content) {
        final SpannableString s = new SpannableString(content);

        //To make sure Font FileName doesn't end up as a clickable url
        Linkify.addLinks(s, Patterns.WEB_URL, null, new Linkify.MatchFilter() {
            @Override
            public boolean acceptMatch(CharSequence seq, int start, int end) {
                String url = seq.subSequence(start, end).toString();
                //Apply the default matcher too. This will remove filenames that matched.
                return !url.contains(".ttf") && Linkify.sUrlMatchFilter.acceptMatch(seq, start, end);
            }
        }, null);
        createAndShowDialog(context, title, s);
    }

    public static void showDialogWithUrlsWithoutTitle(Context context, String content) {
        showDialogWithUrlsInContent(context, null, content);
    }

    //TODO find a better way to show custom view
    private static void createAndShowDialog(Context context, String title,
            SpannableString message) {
        Dialog show = new LovelyInfoDialog(context)
                .setTopColorRes(R.color.colorPrimary)
                .setIcon(R.drawable.ic_info_popup)
                .setTitle(title)
                .setMessage(message)
                .show();
        TextView tvMessage = (TextView) show.findViewById(R.id.ld_message);
        if (tvMessage != null) {
            tvMessage.setMovementMethod(LinkMovementMethod.getInstance());
            tvMessage.setLinkTextColor(ContextCompat.getColor(context, R.color.blue));
        }
    }

    private static void createAndShowDialog(Context context, String title, String content) {
        createAndShowDialog(context, title, content);
    }

    public static void showSimpleDialogWithTitle(Context context, @StringRes int title, String content) {
        createAndShowDialog(context, context.getString(title), content);
    }

    public static void showSimpleDialogWithoutTitle(Context context, String content) {
        createAndShowDialog(context, null, content);
    }

    public static Dialog showProgressUpdateDialog(Context context, String message) {
        return ProgressDialog.show(context, "", message, true, false);
    }
}
