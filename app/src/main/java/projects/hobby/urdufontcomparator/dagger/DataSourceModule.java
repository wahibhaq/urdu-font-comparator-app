package projects.hobby.urdufontcomparator.dagger;

import dagger.Module;
import dagger.Provides;
import projects.hobby.urdufontcomparator.data.FontApi;
import projects.hobby.urdufontcomparator.data.FontApiImpl;

@Module
public class DataSourceModule {

    @Provides
    public FontApi provideFontApi(FontApiImpl impl) {
        return impl;
    }
}
