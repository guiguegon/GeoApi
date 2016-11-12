package es.guiguegon.geoapi.data.repositories.weather;

import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.models.Weather;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public interface WeatherRepository {

    Observable<Weather> getWeatherByLocation(Location location);

    Observable<Boolean> storeWeather(Weather weather);
}
