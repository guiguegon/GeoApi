package es.guiguegon.geoapi.data.repositories.location;

import es.guiguegon.geoapi.data.models.Location;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public interface LocationRepository {

    Observable<Location> getLocationByName(String name);

    Observable<Location> getLocations();

    Observable<Boolean> storeLocation(Location location);
}
