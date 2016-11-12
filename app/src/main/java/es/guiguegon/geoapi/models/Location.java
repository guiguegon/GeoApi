package es.guiguegon.geoapi.models;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class Location {

    private String name;
    private Bbox bbox;
    private String lat;
    private String lng;

    public String getName() {
        return name;
    }

    public Bbox getBbox() {
        return bbox;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}

