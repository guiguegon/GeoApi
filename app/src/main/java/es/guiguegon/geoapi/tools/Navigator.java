package es.guiguegon.geoapi.tools;

import android.content.Context;
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
}
