package es.guiguegon.geoapi.repositories.location;

import es.guiguegon.geoapi.models.Location;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public interface LocationRepository {

    Observable<Location> getLocation(String name);

    Observable<Location> getLocations();

    Observable<Boolean> storeLocation(Location location);
}
