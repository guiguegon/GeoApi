package es.guiguegon.geoapi.features.location;

import es.guiguegon.geoapi.components.base.BaseContract;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.models.Weather;
import java.util.List;

/**
 * Created by guiguegon on 12/11/2016.
 */

public interface LocationContract extends BaseContract {

    interface View extends BaseContract.View {
        void onError();

        void onWeatherReceived(List<Weather> weather);
    }

    interface ActionListener extends BaseContract.ActionListener {
        void storeLocation(Location location);

        void storeWeather(Location location, List<Weather> weather);

        void getWeatherFromLocation(Location location);
    }
}
