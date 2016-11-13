package es.guiguegon.geoapi.tools;

import android.app.Activity;
import android.content.Context;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.features.location.LocationActivity;
import javax.inject.Inject;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class Navigator {

    private Context context;

    @Inject
    public Navigator(Context context) {
        this.context = context;
    }

    public void navigateToLocation(Activity activity, Location location, int requestCode) {
        activity.startActivityForResult(LocationActivity.getCallingIntent(context, location),
                requestCode);
    }
}
