package es.guiguegon.geoapi.components.di.modules;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by guiguegon on 12/11/2016.
 */

@Module
public class NetworkModule {

    private final static int DEFAULT_TIMEOUT = 30;

    private boolean debug;
    private int timeout;

    private NetworkModule(Builder builder) {
        this.debug = builder.debug;
        this.timeout = builder.timeout;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder(
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
    public HttpLoggingInterceptor provideOkHttpLogginClient() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public static class Builder {

        private boolean debug;
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

        public NetworkModule build() {
            return new NetworkModule(this);
        }
    }
}
