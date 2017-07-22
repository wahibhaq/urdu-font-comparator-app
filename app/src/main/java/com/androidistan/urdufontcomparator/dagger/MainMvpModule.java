package com.androidistan.urdufontcomparator.dagger;

import com.google.firebase.database.DatabaseReference;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import com.androidistan.urdufontcomparator.models.UrduTextSource;
import com.androidistan.urdufontcomparator.mvp.MainMvp;
import com.androidistan.urdufontcomparator.mvp.MainPresenter;
import com.androidistan.urdufontcomparator.tracking.TrackingManager;

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
