package upraxis.com.task.di.module;

import android.app.Application;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import upraxis.com.task.BuildConfig;

/**
 * Created by rsbulanon on 6/14/17.
 */
@Module
public class RetrofitModule {
    private static final int CONNECTION_TIME_OUT = 60;
    private static final int READ_TIME_OUT = 60;

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application application) {
        File cacheDir = application.getExternalCacheDir();
        if (cacheDir == null) {
            cacheDir = application.getCacheDir();
        }
        final Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Application application) {
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BuildConfig.HOST_NAME)
                .client(provideOkHttpClient(application))
                .build();
        return retrofit;
    }

}
