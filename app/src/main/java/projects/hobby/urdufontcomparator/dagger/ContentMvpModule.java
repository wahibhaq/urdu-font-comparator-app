package projects.hobby.urdufontcomparator.dagger;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import projects.hobby.urdufontcomparator.models.UrduTextSource;
import projects.hobby.urdufontcomparator.mvp.ContentMvp;
import projects.hobby.urdufontcomparator.mvp.ContentPresenter;
import projects.hobby.urdufontcomparator.utils.CustomFontManager;

@Module
public class ContentMvpModule {

    private final ContentMvp.View view;

    public ContentMvpModule(ContentMvp.View view) {
        this.view = view;
    }

    @Singleton
    @Provides
    ContentMvp.Presenter provideContentMvpPresenter(CustomFontManager fontManager,
                                                    UrduTextSource urduTextSource) {
        return new ContentPresenter(view, fontManager, urduTextSource);
    }

}
