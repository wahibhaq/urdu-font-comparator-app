package com.androidistan.urdufontcomparator.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.androidistan.urdufontcomparator.MainApplication;
import com.androidistan.urdufontcomparator.utils.CustomFontManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = FirebaseModule.class)
public class AppModule {

    private final MainApplication mainApplication;

    public AppModule(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @Singleton
    @Provides
    MainApplication provideApplication() {
        return mainApplication;
    }

    @Singleton
    @Provides
    Context provideApplicationContext() {
        return mainApplication;
    }

    @Singleton
    @Provides
    CustomFontManager provideCustomFontManager(Context context) {
        return new CustomFontManager(context.getAssets());
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
