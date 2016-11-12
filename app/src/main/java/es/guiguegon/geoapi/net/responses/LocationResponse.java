package es.guiguegon.geoapi.net.responses;

import com.google.gson.annotations.SerializedName;
import es.guiguegon.geoapi.models.Location;
import java.util.List;

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
