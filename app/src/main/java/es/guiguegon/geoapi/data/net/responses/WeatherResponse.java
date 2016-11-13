package es.guiguegon.geoapi.data.net.responses;

import java.util.List;

import es.guiguegon.geoapi.data.models.Weather;

/**
 * Created by Guille on 12/11/2016.
 */

public class WeatherResponse {

    List<Weather> weatherObservations;

    public List<Weather> getWeatherObservations() {
        return weatherObservations;
    }
}
