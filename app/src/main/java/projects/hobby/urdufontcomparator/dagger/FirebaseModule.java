package projects.hobby.urdufontcomparator.dagger;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import projects.hobby.urdufontcomparator.R;

@Module
public class FirebaseModule {

    @Singleton
    @Provides
    FirebaseAnalytics provideFirebaseAnalytics(Context context) {
        return FirebaseAnalytics.getInstance(context);
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
