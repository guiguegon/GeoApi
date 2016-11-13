package es.guiguegon.geoapi.data.usecases.location;

import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.repositories.location.LocationRepository;
import es.guiguegon.geoapi.data.usecases.UseCase;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class GetLocationByNameUseCase extends UseCase<Location> {

    private LocationRepository locationRepository;
    private String name;

    @Inject
    public GetLocationByNameUseCase(Executor threadExecutor, Scheduler postExecutionThread,
            LocationRepository locationRepository) {
        super(threadExecutor, postExecutionThread);
        this.locationRepository = locationRepository;
    }

    public GetLocationByNameUseCase setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    protected Observable<Location> buildUseCaseObservable() {
        if (null == name) {
            throw new IllegalStateException("name not set");
        }
        return this.locationRepository.getLocationByName(name);
    }
}
