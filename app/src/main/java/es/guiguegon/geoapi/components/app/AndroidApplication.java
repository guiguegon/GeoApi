package es.guiguegon.geoapi.components.app;

import android.app.Application;
import es.guiguegon.geoapi.BuildConfig;
import es.guiguegon.geoapi.components.di.components.ApplicationComponent;
import es.guiguegon.geoapi.components.di.components.DaggerApplicationComponent;
import es.guiguegon.geoapi.components.di.modules.ApplicationModule;
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
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
