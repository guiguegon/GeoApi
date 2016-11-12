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

public class GetLocationsUseCase extends UseCase<Location> {

    private LocationRepository locationRepository;

    @Inject
    public GetLocationsUseCase(Executor threadExecutor, Scheduler postExecutionThread,
            LocationRepository locationRepository) {
        super(threadExecutor, postExecutionThread);
        this.locationRepository = locationRepository;
    }

    @Override
    protected Observable<Location> buildUseCaseObservable() {
        return this.locationRepository.getLocations();
    }
}
