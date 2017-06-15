package projects.hobby.urdufontcomparator;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class ReleaseApplication extends MainApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());
    }
}