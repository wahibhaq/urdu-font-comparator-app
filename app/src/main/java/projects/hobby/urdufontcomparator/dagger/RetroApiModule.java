package projects.hobby.urdufontcomparator.dagger;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import projects.hobby.urdufontcomparator.data.FontApi;
import projects.hobby.urdufontcomparator.data.RetrofitFontApi;

@Module (includes = NetworkModule.class)
public class RetroApiModule {

    @Singleton
    @Provides
    FontApi provideFontsApi(RetrofitFontApi impl) {
        return impl;
    }
}
