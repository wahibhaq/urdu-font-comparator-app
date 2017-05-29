package projects.hobby.urdufontcomparator.dagger;

import dagger.Subcomponent;
import javax.inject.Singleton;
import projects.hobby.urdufontcomparator.fragments.ContentFragment;

@Singleton
@Subcomponent(modules = {ContentMvpModule.class})
public interface ContentMvpComponent {
    void inject(ContentFragment contentFragment);
}
