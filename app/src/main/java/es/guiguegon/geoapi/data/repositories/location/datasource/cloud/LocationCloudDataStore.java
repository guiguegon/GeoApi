package es.guiguegon.geoapi.data.repositories.location.datasource.cloud;

import es.guiguegon.geoapi.BuildConfig;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.net.GeoService;
import es.guiguegon.geoapi.data.repositories.location.datasource.LocationDataStore;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class LocationCloudDataStore implements LocationDataStore {

    private final static String MAX_ROWS = "20";
    private final static String START_ROW = "0";
    private final static String LANG = "en";
    private final static String IS_NAME_REQUIRED = "true";
    private final static String STYLE = "FULL";

    private GeoService geoService;

    public LocationCloudDataStore(GeoService geoService) {
        this.geoService = geoService;
    }

    @Override
    public Observable<Location> getLocationByName(String name) {
        return geoService.getLocation(name, MAX_ROWS, START_ROW, LANG, IS_NAME_REQUIRED, STYLE,
                BuildConfig.API_USERNAME)
                .flatMapIterable((locationResponse) -> locationResponse.getLocation());
    }

    @Override
    public Observable<Location> getLocations() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Observable<Boolean> storeLocation(Location location) {
        throw new UnsupportedOperationException("not implemented");
    }
}
