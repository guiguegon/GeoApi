package es.guiguegon.geoapi.data.usecases.weather;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.models.Weather;
import es.guiguegon.geoapi.data.repositories.weather.WeatherRepository;
import es.guiguegon.geoapi.data.usecases.UseCase;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class StoreWeatherUseCase extends UseCase<Boolean> {

    private WeatherRepository weatherRepository;
    private Location location;
    private List<Weather> weathers;

    @Inject
    public StoreWeatherUseCase(Executor threadExecutor, Scheduler postExecutionThread,
                               WeatherRepository weatherRepository) {
        super(threadExecutor, postExecutionThread);
        this.weatherRepository = weatherRepository;
    }

    public StoreWeatherUseCase setLocation(Location location) {
        this.location = location;
        return this;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable() {
        if (location == null) {
            throw new IllegalStateException("location not set");
        }
        if (weathers == null) {
            throw new IllegalStateException("weathers not set");
        }
        return this.weatherRepository.storeWeather(location, weathers);
    }
}
