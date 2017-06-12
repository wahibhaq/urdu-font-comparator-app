package projects.hobby.urdufontcomparator.dagger;

import javax.inject.Singleton;

import dagger.Component;
import projects.hobby.urdufontcomparator.activities.BaseActivity;
import projects.hobby.urdufontcomparator.tracking.TrackingManager;

@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    //Subcomponents
    MainMvpComponent mvpComponent(MainMvpModule mainMvpModule);
    ContentMvpComponent contentmvpComponent(ContentMvpModule contentMvpModule);

    TrackingManager getTrackingManager();

}
