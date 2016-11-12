package es.guiguegon.geoapi.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.WorkerThread;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;
/**
 * Created by guiguegon on 07/10/2016.
 */

/**
 * Helper class to manage SQLite Database
 */
public abstract class DBDatabaseHelper extends SQLiteOpenHelper {

    /**
     * DB Parameters
     */
    private static final String TABLE_NAME = "location";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_EXTERNAL_ID = "external_id";
    private static final String COLUMN_DATA = "data";
    private static final String DATABASE_NAME = "locations.db";
    private static final int DATABASE_VERSION = 1;
    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME
            + "( "
            + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_EXTERNAL_ID
            + " text not null unique, "
            + COLUMN_DATA
            + " text not null);";
    private String[] allColumns = {
            COLUMN_ID, COLUMN_EXTERNAL_ID, COLUMN_DATA
    };
    private SQLiteDatabase database;

    @Inject
    public DBDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Timber.d("Upgrading database from version "
                + oldVersion
                + " to "
                + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void open() throws SQLException {
        database = getWritableDatabase();
    }

    /**
     * Store some data in DB using an id (external id) that not will be the primary key
     *
     * @param externalId id
     * @param data       data
     * @return number of affected rows
     */
    @WorkerThread
    long storeData(String externalId, String data) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATA, data);
        values.put(COLUMN_EXTERNAL_ID, externalId);
        long insertId = database.insert(TABLE_NAME, null, values);
        close();
        return insertId;
    }

    /**
     * Delete data with external id
     *
     * @param id external id
     * @return number of affected rows
     */
    @WorkerThread
    long deleteData(String id) {
        open();
        long affectedRows = database.delete(TABLE_NAME, COLUMN_EXTERNAL_ID + " = " + id, null);
        close();
        return affectedRows;
    }

    /**
     * Return some data stored with external id
     *
     * @param id external id
     * @return data
     */
    @WorkerThread
    String getData(String id) {
        open();
        String serializedData = null;
        Cursor cursor =
                database.query(TABLE_NAME, allColumns, COLUMN_EXTERNAL_ID + " = " + id, null, null,
                        null, null);
        if (cursor.moveToFirst()) {
            serializedData = cursorToData(cursor);
        }
        // make sure to close the cursor
        cursor.close();
        close();
        return serializedData;
    }

    /**
     * Return all data stored
     *
     * @return data
     */
    @WorkerThread
    List<String> getAll() {
        open();
        String serializedData = null;
        List<String> allData = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME, allColumns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                serializedData = cursorToData(cursor);
                allData.add(serializedData);
            } while (cursor.moveToNext());
        }
        // make sure to close the cursor
        cursor.close();
        close();
        return allData;
    }

    /**
     * Delete all data contained in DB
     *
     * @return number of affected rows
     */
    @WorkerThread
    Long deleteAll() {
        open();
        long affectedRows = database.delete(TABLE_NAME, null, null);
        close();
        return affectedRows;
    }

    private String cursorToData(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(COLUMN_DATA));
    }
}
