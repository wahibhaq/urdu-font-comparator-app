package projects.hobby.urdufontcomparator.dagger;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {

    //Subcomponent
    MvpComponent mvpComponent(MvpModule mvpModule);

    ContentMvpComponent contentmvpComponent(ContentMvpModule contentMvpModule);

}
