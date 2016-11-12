package es.guiguegon.geoapi.main;

import es.guiguegon.geoapi.components.di.scopes.PerActivity;
import es.guiguegon.geoapi.components.ui.BasePresenter;
import es.guiguegon.geoapi.tools.Navigator;
import javax.inject.Inject;

/**
 * Created by Guille on 12/11/2016.
 */

@PerActivity
public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.ActionListener {

    @Inject
    public MainPresenter(Navigator navigator) {
        super(navigator);
    }
}
