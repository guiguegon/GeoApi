package es.guiguegon.geoapi.components.di.modules;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by guiguegon on 12/11/2016.
 */

@Module
public class ThreadingModule {

    private ThreadingModule(Builder builder) {
    }

    @Provides
    @Singleton
    Executor provideThreadExecutor() {
        return new ThreadPoolExecutor(0, Runtime.getRuntime().availableProcessors(), 60L,
                TimeUnit.SECONDS, new SynchronousQueue<>());
    }

    @Provides
    @Singleton
    Scheduler provideScheduler() {
        return AndroidSchedulers.mainThread();
    }

    public static class Builder {

        public Builder() {
        }

        public ThreadingModule build() {
            return new ThreadingModule(this);
        }
    }
}
