package es.guiguegon.geoapi.repositories.location.datasource;

import es.guiguegon.geoapi.models.Location;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public interface LocationDataStore {

    Observable<Location> getLocationByName(String name);

    Observable<Location> getLocations();

    Observable<Boolean> storeLocation(Location location);
}
