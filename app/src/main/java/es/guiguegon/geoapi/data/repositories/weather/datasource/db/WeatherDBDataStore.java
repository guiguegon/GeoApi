package es.guiguegon.geoapi.data.repositories.weather.datasource.db;

import java.util.List;

import es.guiguegon.geoapi.data.db.DBDatabaseHelper;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.models.Weather;
import es.guiguegon.geoapi.data.repositories.weather.datasource.WeatherDataStore;
import es.guiguegon.geoapi.tools.serializer.GsonSerializer;
import es.guiguegon.geoapi.tools.serializer.Serializer;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class WeatherDBDataStore implements WeatherDataStore {

    private DBDatabaseHelper dbDatabaseHelper;
    private Serializer serializer;

    public WeatherDBDataStore(DBDatabaseHelper dbDatabaseHelper,
                              GsonSerializer gsonSerializer) {
        this.dbDatabaseHelper = dbDatabaseHelper;
        this.serializer = gsonSerializer;
    }

    @Override
    public Observable<List<Weather>> getWeatherByLocation(Location location) {
        return Observable.create(subscriber -> {
            try {
                String serialized = dbDatabaseHelper.getData(location.getName());
                if (serialized != null) {
                    List<Weather> weathers = serializer.deserializeList(serialized, Weather.class);
                    subscriber.onNext(weathers);
                }
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    @Override
    public Observable<Boolean> storeWeather(Location location, List<Weather> weathers) {
        return Observable.create(subscriber -> {
            try {
                String serializedList = serializer.serializeList(weathers);
                if (serializedList != null) {
                    long rows = dbDatabaseHelper.storeData(location.getName(), serializedList);
                    subscriber.onNext(rows > 0);
                }
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}