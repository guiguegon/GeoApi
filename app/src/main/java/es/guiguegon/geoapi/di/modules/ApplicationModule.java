package es.guiguegon.geoapi.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import es.guiguegon.geoapi.app.AndroidApplication;
import javax.inject.Singleton;

/**
 * Created by guiguegon on 12/11/2016.
 */
@Module
public class ApplicationModule {

    private AndroidApplication androidApplication;

    public ApplicationModule(AndroidApplication androidApplication) {
        this.androidApplication = androidApplication;
    }

    @Provides
    @Singleton
    Context getContext() {
        return androidApplication.getApplicationContext();
    }
}
