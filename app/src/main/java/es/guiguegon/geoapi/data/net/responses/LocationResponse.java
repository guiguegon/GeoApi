package es.guiguegon.geoapi.data.net.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import es.guiguegon.geoapi.data.models.Location;

/**
 * Created by Guille on 12/11/2016.
 */

public class LocationResponse {

    private int totalResultsCount;
    @SerializedName("geonames")
    private List<Location> location;

    public int getTotalResultsCount() {
        return totalResultsCount;
    }

    public List<Location> getLocation() {
        return location;
    }
}
