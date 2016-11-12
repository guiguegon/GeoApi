package es.guiguegon.geoapi.data.repositories.weather.datasource.cloud;

import java.util.List;

import es.guiguegon.geoapi.data.models.Bbox;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.models.Weather;
import es.guiguegon.geoapi.data.net.GeoService;
import es.guiguegon.geoapi.data.repositories.weather.datasource.WeatherDataStore;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class WeatherCloudDataStore implements WeatherDataStore {

    private final static String USERNAME = "ilgeonamessample";

    private GeoService geoService;

    public WeatherCloudDataStore(GeoService geoService) {
        this.geoService = geoService;
    }

    @Override
    public Observable<List<Weather>> getWeatherByLocation(Location location) {
        Bbox bbox = location.getBbox();
        return geoService.getWeatherFromLocation(String.valueOf(bbox.getNorth()), String.valueOf(bbox.getSouth()),
                String.valueOf(bbox.getEast()), String.valueOf(bbox.getWest()), USERNAME).map(weatherResponse -> weatherResponse.getWeatherObservations());
    }

    @Override
    public Observable<Boolean> storeWeather(Location location, List<Weather> weathers) {
        throw new UnsupportedOperationException("not implemented");
    }
}
