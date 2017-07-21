package com.androidistan.urdufontcomparator.dagger;

import javax.inject.Singleton;

import dagger.Component;
import com.androidistan.urdufontcomparator.activities.BaseActivity;
import com.androidistan.urdufontcomparator.tracking.TrackingManager;

@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    //Subcomponents
    MainMvpComponent mvpComponent(MainMvpModule mainMvpModule);
    ContentMvpComponent contentmvpComponent(ContentMvpModule contentMvpModule);

    TrackingManager getTrackingManager();

}
