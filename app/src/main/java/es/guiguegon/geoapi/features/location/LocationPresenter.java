package es.guiguegon.geoapi.features.location;

import javax.inject.Inject;

import es.guiguegon.geoapi.components.base.BasePresenter;
import es.guiguegon.geoapi.di.scopes.PerActivity;
import es.guiguegon.geoapi.tools.Navigator;

/**
 * Created by guiguegon on 12/11/2016.
 */

@PerActivity
public class LocationPresenter extends BasePresenter<LocationContract.View>
        implements LocationContract.ActionListener {

    @Inject
    public LocationPresenter(Navigator navigator) {
        super(navigator);
    }

    public void unsubscribeFromUseCases() {
    }
}
