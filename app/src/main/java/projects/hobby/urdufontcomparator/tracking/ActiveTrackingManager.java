package projects.hobby.urdufontcomparator.tracking;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Singleton;

@Singleton
public class ActiveTrackingManager implements TrackingManager {

    private enum CustomEvents {
        EVENT_FONT_SELECT("font_select"),
        EVENT_FONT_DETAILS("font_details"),
        EVENT_FONT_RATING("font_rating"),
        EVENT_FONT_RATING_SUBMIT("font_rating_submit"),
        EVENT_OPEN_LICENSES("open_licenses"),
        EVENT_OPEN_ABOUT_DEVELOPERS("open_about_developers"),
        EVENT_OPEN_CREDITS("open_credits"),
        EVENT_CONTACT_US("contact_us"),
        SEND_EMAIL("send_email"),
        SEND_TWEET("send_tweet"),
        EVENT_DIALOG_SHOWN("dialog_shown"),
        ERROR_DIALOG("error_dialog"),
        EVENT_SHARE_APP_WITH_FRIEND("share_app_with_friend");

        private final String name;

        CustomEvents(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private final AppTracker appTracker;

    public ActiveTrackingManager(AppTracker appTracker) {
        this.appTracker = appTracker;
    }

    @Override
    public void appOpen() {
        Bundle params = new Bundle();
        appTracker.trackEvent(FirebaseAnalytics.Event.APP_OPEN, params);
    }

    @Override
    public void openFontDetails(String fontName) {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, fontName);
        appTracker.trackEvent(CustomEvents.EVENT_FONT_DETAILS.getName(), params);
    }

    @Override
    public void openFontRating(String fontName) {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, fontName);
        appTracker.trackEvent(CustomEvents.EVENT_FONT_RATING.getName(), params);
    }

    @Override
    public void submitFontRating(String fontName, int ratingValue) {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, fontName);
        params.putInt(FirebaseAnalytics.Param.VALUE, ratingValue);
        appTracker.trackEvent(CustomEvents.EVENT_FONT_RATING_SUBMIT.getName(), params);
    }

    @Override
    public void pickFont(String fontName) {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, fontName);
        appTracker.trackEvent(CustomEvents.EVENT_FONT_SELECT.getName(), params);
    }

    @Override
    public void openLicenses() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                CustomEvents.EVENT_OPEN_LICENSES.getName());
        appTracker.trackEvent(CustomEvents.EVENT_OPEN_LICENSES.getName(), params);
    }

    @Override
    public void openAboutDevs() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                CustomEvents.EVENT_OPEN_ABOUT_DEVELOPERS.getName());
        appTracker.trackEvent(CustomEvents.EVENT_OPEN_ABOUT_DEVELOPERS.getName(), params);
    }

    @Override
    public void openCredits() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                CustomEvents.EVENT_OPEN_CREDITS.getName());
        appTracker.trackEvent(CustomEvents.EVENT_OPEN_CREDITS.getName(), params);
    }

    @Override
    public void sendEmail() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                CustomEvents.SEND_EMAIL.getName());
        appTracker.trackEvent(CustomEvents.EVENT_CONTACT_US.getName(), params);
    }

    @Override
    public void sendTweet() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                CustomEvents.SEND_TWEET.getName());
        appTracker.trackEvent(CustomEvents.EVENT_CONTACT_US.getName(), params);
    }

    @Override
    public void errorShown() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                CustomEvents.ERROR_DIALOG.getName());
        appTracker.trackEvent(CustomEvents.EVENT_DIALOG_SHOWN.getName(), params);
    }

    @Override
    public void shareAppWithFriend() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                CustomEvents.EVENT_SHARE_APP_WITH_FRIEND.getName());
        appTracker.trackEvent(CustomEvents.EVENT_SHARE_APP_WITH_FRIEND.getName(), params);
    }
}
