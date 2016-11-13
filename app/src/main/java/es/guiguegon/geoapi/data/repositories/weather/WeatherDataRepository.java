package es.guiguegon.geoapi.data.repositories.weather;

import android.content.Context;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.models.Weather;
import es.guiguegon.geoapi.data.repositories.weather.datasource.WeatherDataFactory;
import es.guiguegon.geoapi.tools.NetworkManager;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class WeatherDataRepository implements WeatherRepository {

    private WeatherDataFactory weatherDataFactory;
    private Context context;
    private NetworkManager networkManager;

    @Inject
    public WeatherDataRepository(Context context, NetworkManager networkManager,
            WeatherDataFactory weatherDataFactory) {
        this.weatherDataFactory = weatherDataFactory;
        this.context = context;
        this.networkManager = networkManager;
    }

    @Override
    public Observable<List<Weather>> getWeatherByLocation(Location location) {
        if (networkManager.isInternetAvailable(context)) {
            return weatherDataFactory.getCloudDataStore().getWeatherByLocation(location);
        } else {
            return weatherDataFactory.getDBDataStore().getWeatherByLocation(location);
        }
    }

    @Override
    public Observable<Boolean> storeWeather(Location location, List<Weather> weathers) {
        return weatherDataFactory.getDBDataStore().storeWeather(location, weathers);
    }
}
