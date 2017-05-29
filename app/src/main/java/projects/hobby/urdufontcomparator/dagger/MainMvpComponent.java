package projects.hobby.urdufontcomparator.dagger;

import dagger.Subcomponent;
import javax.inject.Singleton;
import projects.hobby.urdufontcomparator.fragments.MainFragment;

@Singleton
@Subcomponent(modules = {MainMvpModule.class})
public interface MainMvpComponent {
    void inject(MainFragment baseFragment);
}
