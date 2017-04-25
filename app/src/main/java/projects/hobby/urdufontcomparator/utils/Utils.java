package projects.hobby.urdufontcomparator.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;

import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.util.List;
import projects.hobby.urdufontcomparator.R;

/**
 * Utils Class for common general-purpose functions
 */
public class Utils {

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
        Dialog dialog = new LovelyInfoDialog(context)
                .setTopColorRes(R.color.colorPrimaryLight)
                .setTitle(title)
                .setIcon(R.drawable.ic_info_outline)
                .setMessage(message)
                .show();
        TextView tvMessage = (TextView) dialog.findViewById(R.id.ld_message);
        if (tvMessage != null) {
            tvMessage.setMovementMethod(LinkMovementMethod.getInstance());
            tvMessage.setLinkTextColor(ContextCompat.getColor(context, R.color.blue));
        }
        Button btnOk = (Button) dialog.findViewById(R.id.ld_btn_confirm);
        if (btnOk != null) {
            btnOk.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    private static void createAndShowDialog(Context context, String title, String content) {
        SpannableString s = new SpannableString(content);
        createAndShowDialog(context, title, s);
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

    /**
     * If there is an activity available that can respond to the intent
     * http://developer.android.com/training/basics/intents/sending.html#StartActivity
     */
    public static boolean isIntentSafe(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return activities.size() > 0;
    }

    public static boolean isTwitterInstalled(Context context, Intent intent) {
        PackageManager packManager = context.getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean resolved = false;
        for (ResolveInfo resolveInfo : resolvedInfoList) {
            if (resolveInfo.activityInfo.packageName
                    .startsWith(context.getString(R.string.twitter_identifier))) {
                intent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name);
                resolved = true;
                break;
            }
        }
        return resolved;
    }
}
