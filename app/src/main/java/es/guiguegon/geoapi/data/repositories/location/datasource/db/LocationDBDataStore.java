package es.guiguegon.geoapi.data.repositories.location.datasource.db;

import es.guiguegon.geoapi.data.db.DBDatabaseHelper;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.repositories.location.datasource.LocationDataStore;
import es.guiguegon.geoapi.tools.serializer.GsonSerializer;
import es.guiguegon.geoapi.tools.serializer.Serializer;
import java.util.List;
import rx.Observable;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class LocationDBDataStore implements LocationDataStore {

    private DBDatabaseHelper dbDatabaseHelper;
    private Serializer serializer;

    public LocationDBDataStore(DBDatabaseHelper dbDatabaseHelper,
                               GsonSerializer gsonSerializer) {
        this.dbDatabaseHelper = dbDatabaseHelper;
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
                List<String> serializedList = dbDatabaseHelper.getAll();
                if (null != serializedList) {
                    for (String string : serializedList) {
                        subscriber.onNext(serializer.deserialize(string, Location.class));
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
                if (null != serialized) {
                    long rows = dbDatabaseHelper.storeData(location.getName(), serialized);
                    subscriber.onNext(rows > 0);
                }
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}