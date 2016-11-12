package es.guiguegon.geoapi.repositories.location.datasource.db;

import es.guiguegon.geoapi.models.Location;
import es.guiguegon.geoapi.repositories.location.datasource.LocationDataStore;
import es.guiguegon.geoapi.tools.serializer.GsonSerializer;
import es.guiguegon.geoapi.tools.serializer.Serializer;
import java.util.List;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class LocationDBDataStore implements LocationDataStore {

    private LocationDatabaseHelper locationDatabaseHelper;
    private Serializer serializer;

    public LocationDBDataStore(LocationDatabaseHelper locationDatabaseHelper,
            GsonSerializer gsonSerializer) {
        this.locationDatabaseHelper = locationDatabaseHelper;
        this.serializer = gsonSerializer;
    }

    @Override
    public Observable<Location> getLocationByName(String name) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Observable<Location> getLocations() {
        return Observable.create(subscriber -> {
            try {
                List<String> serializedList = locationDatabaseHelper.getAll();
                if (serializedList != null) {
                    for (String string : serializedList) {
                        Location location = serializer.deserialize(string, Location.class);
                        subscriber.onNext(location);
                    }
                }
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    @Override
    public Observable<Boolean> storeLocation(Location location) {
        return Observable.create(subscriber -> {
            try {
                String serialized = serializer.serialize(location);
                if (serialized != null) {
                    locationDatabaseHelper.storeData(location.getName(), serialized);
                }
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}