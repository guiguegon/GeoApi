package es.guiguegon.geoapi.net.responses;

import es.guiguegon.geoapi.models.Weather;
import java.util.List;

/**
 * Created by Guille on 12/11/2016.
 */

public class WeatherResponse {

    List<Weather> weatherObservations;

    public List<Weather> getWeatherObservations() {
        return weatherObservations;
    }
}
