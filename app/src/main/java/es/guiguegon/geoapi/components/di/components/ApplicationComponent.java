package es.guiguegon.geoapi.components.di.components;

import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;
import es.guiguegon.geoapi.components.app.AndroidApplication;
import es.guiguegon.geoapi.components.di.modules.ApplicationModule;
import es.guiguegon.geoapi.components.di.modules.NetworkModule;
import es.guiguegon.geoapi.components.di.modules.RepositoriesModule;
import es.guiguegon.geoapi.components.di.modules.ThreadingModule;
import es.guiguegon.geoapi.repositories.location.LocationRepository;
import rx.Scheduler;

/**
 * Created by guiguegon on 12/11/2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, ThreadingModule.class, RepositoriesModule.class})
public interface ApplicationComponent {

    void inject(AndroidApplication androidApplication);

    Context context();

    Executor executor();

    Scheduler scheduler();

    LocationRepository locationRepository();
}
