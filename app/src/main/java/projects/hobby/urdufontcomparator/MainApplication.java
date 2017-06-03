package projects.hobby.urdufontcomparator;

import android.app.Application;
import android.content.Context;
import com.google.firebase.FirebaseApp;
import projects.hobby.urdufontcomparator.dagger.AppModule;
import projects.hobby.urdufontcomparator.dagger.ApplicationComponent;
import projects.hobby.urdufontcomparator.dagger.DaggerApplicationComponent;

public class MainApplication extends Application {

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();

        FirebaseApp.initializeApp(this.getApplicationContext());
    }

    public ApplicationComponent getComponent() {
        return appComponent;
    }

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }
}
