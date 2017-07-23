package com.androidistan.urdufontcomparator.dagger;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.androidistan.urdufontcomparator.models.UrduTextSource;
import com.androidistan.urdufontcomparator.mvp.ContentMvp;
import com.androidistan.urdufontcomparator.mvp.ContentPresenter;
import com.androidistan.urdufontcomparator.tracking.TrackingManager;
import com.androidistan.urdufontcomparator.utils.CustomFontManager;

@Module
public class ContentMvpModule {

    private final ContentMvp.View view;

    public ContentMvpModule(ContentMvp.View view) {
        this.view = view;
    }

    @Singleton
    @Provides
    ContentMvp.Presenter provideContentMvpPresenter(CustomFontManager fontManager,
                                                    UrduTextSource urduTextSource,
                                                    TrackingManager trackingManager) {
        return new ContentPresenter(view, fontManager, urduTextSource, trackingManager);
    }

}
