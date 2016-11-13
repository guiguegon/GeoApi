package es.guiguegon.geoapi.data.repositories.location;

import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.repositories.location.datasource.LocationDataFactory;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class LocationDataRepository implements LocationRepository {

    private LocationDataFactory locationDataFactory;

    @Inject
    public LocationDataRepository(LocationDataFactory locationDataFactory) {
        this.locationDataFactory = locationDataFactory;
    }

    @Override
    public Observable<Location> getLocationByName(String name) {
        return locationDataFactory.getCloudDataStore().getLocationByName(name);
    }

    @Override
    public Observable<Location> getLocations() {
        return locationDataFactory.getDBDataStore().getLocations();
    }

    @Override
    public Observable<Boolean> storeLocation(Location location) {
        return locationDataFactory.getDBDataStore().storeLocation(location);
    }

    @Override
    public Observable<Boolean> deleteLocation(Location location) {
        return locationDataFactory.getDBDataStore().deleteLocation(location);
    }
}
