package projects.hobby.urdufontcomparator.tracking;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public final class FirebaseTracker implements AppTracker {

    private final FirebaseAnalytics firebaseAnalytics;

    public FirebaseTracker(FirebaseAnalytics firebaseAnalytics) {
        this.firebaseAnalytics = firebaseAnalytics;
    }

    @Override
    public void trackEvent(String eventName, Bundle bundle) {
        firebaseAnalytics.logEvent(eventName, bundle);
    }
}
