package es.guiguegon.geoapi.data.repositories.weather.datasource;

import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.models.Weather;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public interface WeatherDataStore {

    Observable<Weather> getWeatherByLocation(Location location);

    Observable<Boolean> storeWeather(Weather weather);
}
