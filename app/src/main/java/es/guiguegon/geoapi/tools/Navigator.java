package es.guiguegon.geoapi.tools;

import android.app.Activity;
import android.content.Context;

import javax.inject.Inject;

import es.guiguegon.geoapi.features.location.LocationActivity;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class Navigator {

    private Context context;

    @Inject
    public Navigator(Context context) {
        this.context = context;
    }

    public void navigateToLocation(Activity activity) {
        activity.startActivity(LocationActivity.getCallingIntent(context));
    }
}
