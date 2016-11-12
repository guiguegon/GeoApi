package es.guiguegon.geoapi.usecases.location;

import es.guiguegon.geoapi.models.Location;
import es.guiguegon.geoapi.repositories.location.LocationRepository;
import es.guiguegon.geoapi.usecases.UseCase;
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
        if (name == null) {
            throw new IllegalStateException("name not set");
        }
        return this.locationRepository.getLocation(name);
    }
}
