package es.guiguegon.geoapi.data.repositories.location.datasource;

import javax.inject.Inject;

import es.guiguegon.geoapi.data.net.GeoService;
import es.guiguegon.geoapi.data.repositories.location.datasource.cloud.LocationCloudDataStore;
import es.guiguegon.geoapi.data.repositories.location.datasource.db.LocationDBDataStore;
import es.guiguegon.geoapi.data.repositories.location.datasource.db.LocationDatabaseHelper;
import es.guiguegon.geoapi.tools.serializer.GsonSerializer;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class LocationDataFactory {

    private LocationDatabaseHelper locationDatabaseHelper;
    private GsonSerializer gsonSerializer;
    private GeoService geoService;

    @Inject
    public LocationDataFactory(LocationDatabaseHelper locationDatabaseHelper, GeoService geoService,
            GsonSerializer gsonSerializer) {
        this.locationDatabaseHelper = locationDatabaseHelper;
        this.gsonSerializer = gsonSerializer;
        this.geoService = geoService;
    }

    public LocationDataStore getDBDataStore() {
        return new LocationDBDataStore(locationDatabaseHelper, gsonSerializer);
    }

    public LocationDataStore getCloudDataStore() {
        return new LocationCloudDataStore(geoService);
    }
}
