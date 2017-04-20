package projects.hobby.urdufontcomparator.dagger;

import javax.inject.Singleton;

import dagger.Subcomponent;
import projects.hobby.urdufontcomparator.fragments.MainFragment;

@Singleton
@Subcomponent(modules = {MainMvpModule.class})
public interface MainMvpComponent {
    void inject(MainFragment baseFragment);
}
