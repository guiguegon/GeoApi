package es.guiguegon.geoapi.data.repositories.weather;

import javax.inject.Inject;

import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.models.Weather;
import es.guiguegon.geoapi.data.repositories.weather.datasource.WeatherDataFactory;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class WeatherDataRepository implements WeatherRepository {

    private WeatherDataFactory weatherDataFactory;

    @Inject
    public WeatherDataRepository(WeatherDataFactory weatherDataFactory) {
        this.weatherDataFactory = weatherDataFactory;
    }

    @Override
    public Observable<Weather> getWeatherByLocation(Location location) {
        return weatherDataFactory.getCloudDataStore().getWeatherByLocation(location);
    }

    @Override
    public Observable<Boolean> storeWeather(Weather weather) {
        return weatherDataFactory.getDBDataStore().storeWeather(weather);
    }
}
