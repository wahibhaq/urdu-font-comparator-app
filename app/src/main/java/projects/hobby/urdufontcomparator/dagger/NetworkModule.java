package projects.hobby.urdufontcomparator.dagger;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder builder
                = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        return builder.build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(
            OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl("https://quarkbackend.com/getfile/wahib-tech/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
