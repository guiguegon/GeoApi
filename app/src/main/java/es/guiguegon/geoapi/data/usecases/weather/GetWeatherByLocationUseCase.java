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

public class GetWeatherByLocationUseCase extends UseCase<List<Weather>> {

    private WeatherRepository weatherRepository;
    private Location location;

    @Inject
    public GetWeatherByLocationUseCase(Executor threadExecutor, Scheduler postExecutionThread,
                                       WeatherRepository weatherRepository) {
        super(threadExecutor, postExecutionThread);
        this.weatherRepository = weatherRepository;
    }

    public GetWeatherByLocationUseCase setLocation(Location location) {
        this.location = location;
        return this;
    }

    @Override
    protected Observable<List<Weather>> buildUseCaseObservable() {
        if (location == null) {
            throw new IllegalStateException("location not set");
        }
        return this.weatherRepository.getWeatherByLocation(location);
    }
}
