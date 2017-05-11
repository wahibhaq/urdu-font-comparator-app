package projects.hobby.urdufontcomparator.data;

import java.util.List;
import projects.hobby.urdufontcomparator.models.UrduFont;
import rx.Observable;

public interface FontApi {

    Observable<List<UrduFont>> getFonts();

}
