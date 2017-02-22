package projects.hobby.urdufontcomparator.dagger;

import dagger.Component;
import javax.inject.Singleton;
import projects.hobby.urdufontcomparator.fragments.MainFragment;

@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {

    void inject(MainFragment baseFragment);

}
