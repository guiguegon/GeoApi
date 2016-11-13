package es.guiguegon.geoapi.components.app;

import android.app.Application;
import es.guiguegon.geoapi.BuildConfig;
import es.guiguegon.geoapi.di.components.ApplicationComponent;
import es.guiguegon.geoapi.di.components.DaggerApplicationComponent;
import es.guiguegon.geoapi.di.modules.ApplicationModule;
import es.guiguegon.geoapi.di.modules.NetworkModule;
import es.guiguegon.geoapi.di.modules.RepositoriesModule;
import es.guiguegon.geoapi.di.modules.ThreadingModule;
import timber.log.Timber;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class AndroidApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        initTimber();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            //plant production tree
        }
    }

    private void initDagger() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule.Builder().setDebug(BuildConfig.DEBUG)
                        .setBaseUrl(BuildConfig.API_URL)
                        .build())
                .repositoriesModule(new RepositoriesModule())
                .threadingModule(new ThreadingModule())
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
