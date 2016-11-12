package es.guiguegon.geoapi.components.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by guiguegon on 12/11/2016.
 */

@Module
public class NetworkModule {

    private final static int DEFAULT_TIMEOUT = 30;

    private boolean debug;
    private int timeout;
    private String baseUrl;

    private NetworkModule(Builder builder) {
        this.debug = builder.debug;
        this.timeout = builder.timeout;
        this.baseUrl = builder.baseUrl;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClientBuilder(
            HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(timeout, TimeUnit.SECONDS);
        clientBuilder.readTimeout(timeout, TimeUnit.SECONDS);
        if (debug) {
            clientBuilder.addInterceptor(httpLoggingInterceptor);
        }
        return clientBuilder;
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideOkHttpLogginClient() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static class Builder {

        private boolean debug;
        private String baseUrl;
        private int timeout = DEFAULT_TIMEOUT;

        public Builder() {
        }

        public Builder setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public NetworkModule build() {
            return new NetworkModule(this);
        }
    }
}
