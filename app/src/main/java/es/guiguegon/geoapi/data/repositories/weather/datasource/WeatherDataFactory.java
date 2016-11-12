package es.guiguegon.geoapi.data.repositories.weather.datasource;

import javax.inject.Inject;

import es.guiguegon.geoapi.data.net.GeoService;
import es.guiguegon.geoapi.data.repositories.weather.datasource.cloud.WeatherCloudDataStore;
import es.guiguegon.geoapi.data.repositories.weather.datasource.db.WeatherDBDataStore;
import es.guiguegon.geoapi.data.repositories.weather.datasource.db.WeatherDatabaseHelper;
import es.guiguegon.geoapi.tools.serializer.GsonSerializer;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class WeatherDataFactory {

    private WeatherDatabaseHelper weatherDatabaseHelper;
    private GsonSerializer gsonSerializer;
    private GeoService geoService;

    @Inject
    public WeatherDataFactory(WeatherDatabaseHelper weatherDatabaseHelper, GeoService geoService,
                              GsonSerializer gsonSerializer) {
        this.weatherDatabaseHelper = weatherDatabaseHelper;
        this.gsonSerializer = gsonSerializer;
        this.geoService = geoService;
    }

    public WeatherDataStore getDBDataStore() {
        return new WeatherDBDataStore(weatherDatabaseHelper, gsonSerializer);
    }

    public WeatherDataStore getCloudDataStore() {
        return new WeatherCloudDataStore(geoService);
    }
}
