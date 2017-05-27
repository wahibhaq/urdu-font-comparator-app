package projects.hobby.urdufontcomparator.dagger;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import projects.hobby.urdufontcomparator.data.FontApi;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.mvp.MainMvp;
import projects.hobby.urdufontcomparator.mvp.MainPresenter;

@Module
public class MainMvpModule {

    private final MainMvp.View view;

    public MainMvpModule(MainMvp.View view) {
        this.view = view;
    }

    @Singleton
    @Provides
    MainMvp.Presenter provideMainMvpPresenter(UrduTextSource urduTextSource, FontApi fontSource) {
        return new MainPresenter(view, urduTextSource, fontSource);
    }
}
