package com.androidistan.urdufontcomparator;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;

import com.androidistan.urdufontcomparator.dagger.AppModule;
import com.androidistan.urdufontcomparator.dagger.ApplicationComponent;
import com.androidistan.urdufontcomparator.dagger.DaggerApplicationComponent;

public class MainApplication extends Application {

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();


        FirebaseApp.initializeApp(this.getApplicationContext());
        getComponent().getTrackingManager().appOpen();
    }

    public ApplicationComponent getComponent() {
        return appComponent;
    }

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }
}
