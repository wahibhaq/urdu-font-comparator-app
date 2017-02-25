package projects.hobby.urdufontcomparator.models;

import com.google.gson.annotations.SerializedName;
import java.util.Collections;
import java.util.List;
import rx.functions.Func1;

/**
 * Unwraps the {@link UrduFont} from a {@link FontEntriesResponse}
 */
public class FontEntriesResponse {

    public static final Func1<FontEntriesResponse, List<UrduFont>>
            UNWRAP_FUNCTION = new Func1<FontEntriesResponse, List<UrduFont>>() {
        @Override
        public List<UrduFont> call(FontEntriesResponse fontEntriesREsponse) {
            return fontEntriesREsponse.getFonts();
        }
    };

    @SerializedName("fonts")
    private List<UrduFont> fontList;

    public List<UrduFont> getFonts() {
        if (fontList == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(fontList);
    }
}
