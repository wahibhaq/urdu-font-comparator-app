package projects.hobby.urdufontcomparator.dagger;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import projects.hobby.urdufontcomparator.R;
import projects.hobby.urdufontcomparator.tracking.AppTracker;
import projects.hobby.urdufontcomparator.tracking.FirebaseTracker;

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
        firebaseDatabase.setPersistenceEnabled(true);
        return firebaseDatabase;
    }

    @Singleton
    @Provides
    DatabaseReference provideDatabaseReference(Context context, FirebaseDatabase firebaseDatabase) {
        return firebaseDatabase.getReference(context.getString(R.string.fonts));
    }

}
