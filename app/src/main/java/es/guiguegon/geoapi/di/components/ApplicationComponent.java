package es.guiguegon.geoapi.di.components;

import android.content.Context;
import dagger.Component;
import es.guiguegon.geoapi.app.AndroidApplication;
import es.guiguegon.geoapi.di.modules.ApplicationModule;
import es.guiguegon.geoapi.di.modules.NetworkModule;
import es.guiguegon.geoapi.di.modules.ThreadingModule;
import javax.inject.Singleton;

/**
 * Created by guiguegon on 12/11/2016.
 */
@Singleton
@Component(modules = { ApplicationModule.class, NetworkModule.class, ThreadingModule.class })
public interface ApplicationComponent {

    void inject(AndroidApplication androidApplication);

    Context context();
}
