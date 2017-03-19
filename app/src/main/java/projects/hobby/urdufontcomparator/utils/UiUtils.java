package projects.hobby.urdufontcomparator.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.Button;
import android.widget.TextView;
import projects.hobby.urdufontcomparator.R;

/**
 * General Utils Class for common general-purpose UI functions
 */
public class UiUtils {
    //TODO provide context via Dagger Module

    public static String getLineSpacingsWithDash() {
        return "\n-------------\n";
    }

    public static String getLineSpacings() {
        return "\n\n";
    }


    public static void showDialogWithUrlsWithTitle(Context context, @StringRes int title,
            String content) {
        showDialogWithUrlsInContent(context, context.getString(title), content);
    }

    private static void showDialogWithUrlsInContent(Context context, String title,
            String content) {
        final TextView message = new TextView(context);
        final SpannableString s = new SpannableString(content);
        Linkify.addLinks(s, Linkify.WEB_URLS);
        message.setText(s);
        message.setMovementMethod(LinkMovementMethod.getInstance());
        message.setLinkTextColor(ContextCompat.getColor(context, R.color.blue));

        createAndShowDialog(context, title, message);
    }

    public static void showDialogWithUrlsWithoutTitle(Context context, String content) {
        showDialogWithUrlsInContent(context, null, content);
    }

    //TODO find a better way to show custom view
    private static void createAndShowDialog(Context context, String title,
            TextView message) {
        message.setPadding(100,10,10,10);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setPositiveButton(context.getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }})
                .setView(message);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        //Setting textColor of button
        Button posButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if (posButton!= null) {
            posButton.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
            posButton.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
    }

    private static void createAndShowDialog(Context context, String title, String content) {
        final TextView message = new TextView(context);
        message.setText(content);
        createAndShowDialog(context, title, message);
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
