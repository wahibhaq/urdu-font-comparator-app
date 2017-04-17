package projects.hobby.urdufontcomparator.dagger;

import dagger.Subcomponent;

import javax.inject.Singleton;

import projects.hobby.urdufontcomparator.fragments.ContentFragment;
import projects.hobby.urdufontcomparator.fragments.MainFragment;
import projects.hobby.urdufontcomparator.mvp.ContentMvp;

@Singleton
@Subcomponent(modules = {MvpModule.class})
public interface MvpComponent {
    void inject(MainFragment baseFragment);
}
