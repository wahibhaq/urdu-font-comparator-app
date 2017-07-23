package com.androidistan.urdufontcomparator.dagger;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.androidistan.urdufontcomparator.BuildConfig;
import com.androidistan.urdufontcomparator.R;
import com.androidistan.urdufontcomparator.tracking.ActiveTrackingManager;
import com.androidistan.urdufontcomparator.tracking.AppTracker;
import com.androidistan.urdufontcomparator.tracking.TrackingManager;
import com.androidistan.urdufontcomparator.tracking.FirebaseTracker;

@Module
public class FirebaseModule {

    @Singleton
    @Provides
    AppTracker provideFirebaseAnalytics(Context context) {
        FirebaseAnalytics analytics = FirebaseAnalytics.getInstance(context);
        return new FirebaseTracker(analytics);
    }

    @Singleton
    @Provides
    FirebaseDatabase provideFirebaseDatabase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true); //for offline access
        return firebaseDatabase;
    }

    @Singleton
    @Provides
    DatabaseReference provideDatabaseReference(Context context, FirebaseDatabase firebaseDatabase) {
        DatabaseReference fontsRef = firebaseDatabase.getReference(
                context.getString(R.string.fonts));
        fontsRef.keepSynced(BuildConfig.DEBUG); //force sync on debug
        return firebaseDatabase.getReference(context.getString(R.string.fonts));
    }

    @Singleton
    @Provides
    TrackingManager provideEventsManager(AppTracker appTracker) {
        return new ActiveTrackingManager(appTracker);
    }

}
