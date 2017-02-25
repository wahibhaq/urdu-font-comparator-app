package projects.hobby.urdufontcomparator.data;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import projects.hobby.urdufontcomparator.models.FontEntriesResponse;
import projects.hobby.urdufontcomparator.models.UrduFont;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Singleton
public class FontApiImpl implements FontApi {

    private final Context context;

    private final Gson gson;

    @Inject
    public FontApiImpl(Context context, Gson gson) {
        this.context = context;
        this.gson = gson;
    }

    @Override
    public Observable<List<UrduFont>> getFonts() {
        return Observable.just("fonts_source.json")
                .flatMap(new Func1<String, Observable<InputStream>>() {
                    @Override
                    public Observable<InputStream> call(String fileName) {
                        Log.i("UrduApp", "Filename of json: " + fileName);
                        try {
                            return Observable.just(context.getAssets().open(fileName));
                        } catch (IOException e) {
                            return Observable.error(e);
                        }
                    }
                })
                .map(new Func1<InputStream, FontEntriesResponse>() {
                    @Override
                    public FontEntriesResponse call(InputStream inputStream) {
                        return gson.fromJson(new InputStreamReader(inputStream),
                                FontEntriesResponse.class);
                    }
                })
                .map(FontEntriesResponse.UNWRAP_FUNCTION)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());
    }
}
