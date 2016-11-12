package es.guiguegon.geoapi.main;

import es.guiguegon.geoapi.components.ui.BaseContract;
import es.guiguegon.geoapi.models.Location;

/**
 * Created by guiguegon on 12/11/2016.
 */

public interface MainContract extends BaseContract {

    interface View extends BaseContract.View {
        void onError();

        void onLocationReceived(Location location);

        void onQueryReceived(Location location);
    }

    interface ActionListener extends BaseContract.ActionListener {
        void getLocations();

        void queryLocationByName(String name);
    }
}
