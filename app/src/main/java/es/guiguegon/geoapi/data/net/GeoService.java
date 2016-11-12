package es.guiguegon.geoapi.data.net;
/**
 * Created by guiguegon on 12/11/2016.
 */

import es.guiguegon.geoapi.data.net.responses.LocationResponse;
import es.guiguegon.geoapi.data.net.responses.WeatherResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Retrofit2 interface. It models the webservices
 */
public interface GeoService {

    @GET("searchJSON")
    Observable<LocationResponse> getLocation(@Query("q") String name,
            @Query("maxRows") String maxRows, @Query("startRow") String startRow,
            @Query("lang") String lang, @Query("isNameRequired") String isNameRequired,
            @Query("style") String style, @Query("username") String username);

    @GET("weatherJSON")
    Observable<WeatherResponse> getWeatherFromLocation(@Query("north") String north,
            @Query("south") String south, @Query("east") String east, @Query("west") String west,
            @Query("username") String username);
}
