package es.guiguegon.geoapi.di.components;

import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;
import es.guiguegon.geoapi.components.app.AndroidApplication;
import es.guiguegon.geoapi.data.repositories.location.LocationRepository;
import es.guiguegon.geoapi.data.repositories.weather.WeatherRepository;
import es.guiguegon.geoapi.di.modules.ApplicationModule;
import es.guiguegon.geoapi.di.modules.NetworkModule;
import es.guiguegon.geoapi.di.modules.RepositoriesModule;
import es.guiguegon.geoapi.di.modules.ThreadingModule;
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

    WeatherRepository weatherRepository();
}
