package com.androidistan.urdufontcomparator.dagger;

import com.androidistan.urdufontcomparator.mvp.MainMvp;
import com.androidistan.urdufontcomparator.mvp.MainPresenter;
import com.androidistan.urdufontcomparator.tracking.TrackingManager;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainMvpModule {

    private final MainMvp.View view;

    public MainMvpModule(MainMvp.View view) {
        this.view = view;
    }

    @Singleton
    @Provides
    MainMvp.Presenter provideMainMvpPresenter(DatabaseReference databaseReference,
                                              TrackingManager trackingManager) {
        return new MainPresenter(view, databaseReference, trackingManager);
    }
}
