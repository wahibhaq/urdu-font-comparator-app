package projects.hobby.urdufontcomparator.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import projects.hobby.urdufontcomparator.MainApplication;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.utils.CustomFontManager;

@Module
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
    UrduTextSource provideUrduTextSource(Context context) {
        return new UrduTextSource(context);
    }

    @Singleton
    @Provides SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
