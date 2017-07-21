package com.androidistan.urdufontcomparator.dagger;

import dagger.Subcomponent;
import javax.inject.Singleton;
import com.androidistan.urdufontcomparator.fragments.MainFragment;

@Singleton
@Subcomponent(modules = {MainMvpModule.class})
public interface MainMvpComponent {
    void inject(MainFragment baseFragment);
}
