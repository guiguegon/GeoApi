package es.guiguegon.geoapi.features.main;

import es.guiguegon.geoapi.components.base.BaseContract;
import es.guiguegon.geoapi.data.models.Location;

/**
 * Created by guiguegon on 12/11/2016.
 */

public interface MainContract extends BaseContract {

    interface View extends BaseContract.View {
        void onError();

        void onLocationReceived(Location location);

        void onQueryReceived(Location location);

        void onLocationEnd();
    }

    interface ActionListener extends BaseContract.ActionListener {
        void getLocations();

        void queryLocationByName(String name);
    }
}
