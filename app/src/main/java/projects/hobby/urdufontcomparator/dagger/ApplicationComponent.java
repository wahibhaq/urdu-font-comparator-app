package projects.hobby.urdufontcomparator.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RetroApiModule.class})
public interface ApplicationComponent {

    //Subcomponent
    MainMvpComponent mvpComponent(MainMvpModule mainMvpModule);

    ContentMvpComponent contentmvpComponent(ContentMvpModule contentMvpModule);

}
