package projects.hobby.urdufontcomparator.dagger;

import com.google.firebase.database.DatabaseReference;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.mvp.MainMvp;
import projects.hobby.urdufontcomparator.mvp.MainPresenter;
import projects.hobby.urdufontcomparator.tracking.AppTracker;
import projects.hobby.urdufontcomparator.tracking.TrackingManager;

@Module
public class MainMvpModule {

    private final MainMvp.View view;

    public MainMvpModule(MainMvp.View view) {
        this.view = view;
    }

    @Singleton
    @Provides
    MainMvp.Presenter provideMainMvpPresenter(UrduTextSource urduTextSource,
                                              DatabaseReference databaseReference,
                                              TrackingManager trackingManager) {
        return new MainPresenter(view, urduTextSource, databaseReference, trackingManager);
    }
}
