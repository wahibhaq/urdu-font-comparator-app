package projects.hobby.urdufontcomparator.dagger;

import javax.inject.Singleton;

import dagger.Component;
import projects.hobby.urdufontcomparator.activities.BaseActivity;

@Singleton
@Component(modules = {AppModule.class, RetroApiModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    //Subcomponent
    MainMvpComponent mvpComponent(MainMvpModule mainMvpModule);

    ContentMvpComponent contentmvpComponent(ContentMvpModule contentMvpModule);

}
