package projects.hobby.urdufontcomparator.dagger;

import dagger.Subcomponent;
import javax.inject.Singleton;
import projects.hobby.urdufontcomparator.fragments.MainFragment;

@Singleton
@Subcomponent(modules = {MvpModule.class})
public interface MvpComponent {
    void inject(MainFragment baseFragment);
}
