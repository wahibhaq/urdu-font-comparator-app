package projects.hobby.urdufontcomparator.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.mvp.MainMvp;
import projects.hobby.urdufontcomparator.mvp.MainPresenter;
import projects.hobby.urdufontcomparator.utils.CustomFontManager;

@Module
public class MainMvpModule {

    private final MainMvp.View view;

    public MainMvpModule(MainMvp.View view) {
        this.view = view;
    }

    @Singleton
    @Provides
    MainMvp.Presenter provideMainMvpPresenter(CustomFontManager fontManager,
                                              UrduTextSource urduTextSource) {
        return new MainPresenter(view, fontManager, urduTextSource);
    }
}
