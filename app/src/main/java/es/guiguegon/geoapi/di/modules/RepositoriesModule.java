package es.guiguegon.geoapi.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.guiguegon.geoapi.data.repositories.location.LocationDataRepository;
import es.guiguegon.geoapi.data.repositories.location.LocationRepository;
import es.guiguegon.geoapi.data.repositories.weather.WeatherDataRepository;
import es.guiguegon.geoapi.data.repositories.weather.WeatherRepository;

/**
 * Created by guiguegon on 12/11/2016.
 */

@Module
public class RepositoriesModule {

    @Provides
    @Singleton
    LocationRepository provideLocationRepository(LocationDataRepository locationDataRepository) {
        return locationDataRepository;
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherDataRepository weatherDataRepository) {
        return weatherDataRepository;
    }
}
