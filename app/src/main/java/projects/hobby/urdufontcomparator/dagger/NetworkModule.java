package projects.hobby.urdufontcomparator.dagger;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import projects.hobby.urdufontcomparator.BuildConfig;
import projects.hobby.urdufontcomparator.utils.Utils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

/**
 * Not in use anymore but I am keeping it for future just in case if needed
 * to integrate any external api endpoint
 */
@SuppressWarnings("WeakerAccess")
@Module
public class NetworkModule {

    private static final String HTTPS_QUARKBACKEND_END_POINT =
            "https://quarkbackend.com/getfile/wahib-tech/";

    private static final String CACHE_CONTROL = "Cache-Control";
    public static final int CACHE_MAX_AGE = 2; //2 Minutes
    public static final int CACHE_MAX_STALE = 60; //60 Days, to be on safe side
    public static final int CACHE_SIZE = 10 * 1024 * 1024; //10 MiB

    @Singleton
    @Provides
    protected Cache provideCache(Context context) {
        return new Cache(context.getCacheDir(), CACHE_SIZE);
    }

    @Singleton
    @Provides
    protected OkHttpClient provideOkHttpClient(final Context context, Cache cache) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(provideHttpLoggingInterceptor())
                .addInterceptor(provideOfflineCacheInterceptor(context))
                .addNetworkInterceptor(provideCacheInterceptor());
        return builder.build();
    }

    @Singleton
    @Provides
    protected Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(HTTPS_QUARKBACKEND_END_POINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log (String message) {
                        Timber.d(NetworkModule.class.getSimpleName(), message);
                    }
                });

        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HEADERS : NONE);
        return httpLoggingInterceptor;
    }

    private static Interceptor provideCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept (Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                // re-write response header to force use of cache
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(CACHE_MAX_AGE, TimeUnit.MINUTES)
                        .build();

                return response.newBuilder()
                        .header(CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }

    private static Interceptor provideOfflineCacheInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept (Chain chain) throws IOException {
                Request request = chain.request();

                if (!Utils.isOnline(context)) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(CACHE_MAX_STALE, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed( request );
            }
        };
    }

}
