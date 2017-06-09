package projects.hobby.urdufontcomparator.tracking;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Singleton;

@Singleton
public class ActiveTrackingManager implements TrackingManager {

    private enum EventTypes {
        DEFAULT ("default");

        private final String name;

        EventTypes(String name) {
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
        params.putString(FirebaseAnalytics.Param.CONTENT_TYPE, EventTypes.DEFAULT.name);
        appTracker.trackEvent(FirebaseAnalytics.Event.APP_OPEN, params);
    }
}
