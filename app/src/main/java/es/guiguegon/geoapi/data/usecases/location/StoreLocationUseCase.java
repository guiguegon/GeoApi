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

public class StoreLocationUseCase extends UseCase<Boolean> {

    private LocationRepository locationRepository;
    private Location location;

    @Inject
    public StoreLocationUseCase(Executor threadExecutor, Scheduler postExecutionThread,
            LocationRepository locationRepository) {
        super(threadExecutor, postExecutionThread);
        this.locationRepository = locationRepository;
    }

    public StoreLocationUseCase setLocation(Location location) {
        this.location = location;
        return this;
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable() {
        if (null == location) {
            throw new IllegalStateException("location not set");
        }
        return this.locationRepository.storeLocation(location);
    }
}
