package projects.hobby.urdufontcomparator.dagger;

import javax.inject.Singleton;

import dagger.Subcomponent;
import projects.hobby.urdufontcomparator.fragments.ContentFragment;

@Singleton
@Subcomponent(modules = {ContentMvpModule.class})
public interface ContentMvpComponent {
    void inject(ContentFragment contentFragment);
}
