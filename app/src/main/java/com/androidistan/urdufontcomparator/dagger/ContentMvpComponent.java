package com.androidistan.urdufontcomparator.dagger;

import dagger.Subcomponent;
import javax.inject.Singleton;
import com.androidistan.urdufontcomparator.fragments.ContentFragment;

@Singleton
@Subcomponent(modules = {ContentMvpModule.class})
public interface ContentMvpComponent {
    void inject(ContentFragment contentFragment);
}
