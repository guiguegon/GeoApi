package es.guiguegon.geoapi.data.repositories.weather.datasource;

import java.util.List;

import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.models.Weather;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public interface WeatherDataStore {

    Observable<List<Weather>> getWeatherByLocation(Location location);

    Observable<Boolean> storeWeather(Location location, List<Weather> weathers);
}
