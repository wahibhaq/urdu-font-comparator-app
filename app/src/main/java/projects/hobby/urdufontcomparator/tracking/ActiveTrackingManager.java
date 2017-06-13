package projects.hobby.urdufontcomparator.tracking;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Singleton;

@Singleton
public class ActiveTrackingManager implements TrackingManager {

    private enum EventExtendedParamValue {
        EVENT_FONT_SELECT ("font_select"),
        EVENT_FONT_DETAILS ("font_details"),
        FONT_RATING ("font_rating"),
        EVENT_FONT_RATING_SUBMIT ("font_rating_submit"),
        OPEN_LICENSES ("open_licenses"),
        OPEN_ABOUT_DEVELOPERS ("open_about_developers"),
        OPEN_CREDITS ("open_credits"),
        SEND_EMAIL ("send_email"),
        SEND_TWEET ("send_tweet"),
        ERROR_SHOWN ("error_shown");

        private final String name;

        EventExtendedParamValue(String name) {
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
        appTracker.trackEvent(EventExtendedParamValue.EVENT_FONT_DETAILS.getName(), params);
    }

    @Override
    public void openFontRating(String fontName) {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, fontName);
        params.putString(FirebaseAnalytics.Param.ITEM_CATEGORY,
                EventExtendedParamValue.FONT_RATING.getName());
        appTracker.trackEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }

    @Override
    public void submitFontRating(String fontName, int ratingValue) {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, fontName);
        params.putInt(FirebaseAnalytics.Param.VALUE, ratingValue);
        appTracker.trackEvent(EventExtendedParamValue.EVENT_FONT_RATING_SUBMIT.getName(), params);
    }

    @Override
    public void pickFont(String fontName) {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, fontName);
        appTracker.trackEvent(EventExtendedParamValue.EVENT_FONT_SELECT.getName(), params);
    }

    @Override
    public void openLicenses() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                EventExtendedParamValue.OPEN_LICENSES.getName());
        appTracker.trackEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }

    @Override
    public void openAboutDevs() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                EventExtendedParamValue.OPEN_ABOUT_DEVELOPERS.getName());
        appTracker.trackEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }

    @Override
    public void openCredits() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                EventExtendedParamValue.OPEN_CREDITS.getName());
        appTracker.trackEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }

    @Override
    public void sendEmail() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                EventExtendedParamValue.SEND_EMAIL.getName());
        appTracker.trackEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }

    @Override
    public void sendTweet() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                EventExtendedParamValue.SEND_TWEET.getName());
        appTracker.trackEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }

    @Override
    public void errorShown() {
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME,
                EventExtendedParamValue.ERROR_SHOWN.getName());
        appTracker.trackEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
    }
}
