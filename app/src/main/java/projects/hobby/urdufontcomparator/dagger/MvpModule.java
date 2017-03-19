package projects.hobby.urdufontcomparator.dagger;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import projects.hobby.urdufontcomparator.mvp.MainMvp;
import projects.hobby.urdufontcomparator.mvp.MainPresenter;
import projects.hobby.urdufontcomparator.utils.CustomFontManager;

@Module
public class MvpModule {

    private final MainMvp.View view;

    public MvpModule(MainMvp.View view) {
        this.view = view;
    }

    @Singleton
    @Provides
    MainMvp.Presenter provideMainMvpPresenter(CustomFontManager fontManager) {
        return new MainPresenter(view, fontManager);
    }
}