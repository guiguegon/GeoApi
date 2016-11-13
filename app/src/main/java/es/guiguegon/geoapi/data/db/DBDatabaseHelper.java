package es.guiguegon.geoapi.data.db;

import android.support.annotation.WorkerThread;

import java.util.List;
/**
 * Created by guiguegon on 07/10/2016.
 */

/**
 * Helper class to manage SQLite Database
 */
public interface DBDatabaseHelper {

    /**
     * Store some data in DB using an id (external id) that not will be the primary key
     *
     * @param externalId id
     * @param data       data
     * @return number of affected rows
     */
    @WorkerThread
    long storeData(String externalId, String data);

    /**
     * Delete data with external id
     *
     * @param id external id
     * @return number of affected rows
     */
    @WorkerThread
    long deleteData(String id);

    /**
     * Return some data stored with external id
     *
     * @param id external id
     * @return data
     */
    @WorkerThread
    String getData(String id);

    /**
     * Return all data stored
     *
     * @return data
     */
    @WorkerThread
    List<String> getAll();

    /**
     * Delete all data contained in DB
     *
     * @return number of affected rows
     */
    @WorkerThread
    long deleteAll();
}
