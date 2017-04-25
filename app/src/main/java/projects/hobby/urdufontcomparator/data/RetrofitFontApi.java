package projects.hobby.urdufontcomparator.data;

import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import projects.hobby.urdufontcomparator.models.UrduFont;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Singleton
public class RetrofitFontApi implements FontApi {

    private final RetrofitService retrofitService;

    @Inject
    public RetrofitFontApi(@NonNull Retrofit retrofit) {
        retrofitService = retrofit.create(RetrofitService.class);
    }

    @Override
    public Observable<List<UrduFont>> getFonts() {

        return retrofitService.getFonts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<List<UrduFont>, List<UrduFont>>() {
                    @Override
                    public List<UrduFont> call(List<UrduFont> urduFonts) {
                        return urduFonts;
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    private interface RetrofitService {

        @GET("bins/118maj")
        Observable<List<UrduFont>> getFonts();
    }
}
