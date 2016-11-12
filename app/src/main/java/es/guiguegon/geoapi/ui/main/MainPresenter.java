package es.guiguegon.geoapi.ui.main;

import javax.inject.Inject;

import es.guiguegon.geoapi.components.base.BasePresenter;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.usecases.location.GetLocationByNameUseCase;
import es.guiguegon.geoapi.data.usecases.location.GetLocationsUseCase;
import es.guiguegon.geoapi.di.scopes.PerActivity;
import es.guiguegon.geoapi.tools.Navigator;

/**
 * Created by guiguegon on 12/11/2016.
 */

@PerActivity
public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.ActionListener {

    private GetLocationByNameUseCase getLocationByNameUseCase;
    private GetLocationsUseCase getLocationsUseCase;

    @Inject
    public MainPresenter(GetLocationByNameUseCase getLocationByNameUseCase,
            GetLocationsUseCase getLocationsUseCase, Navigator navigator) {
        super(navigator);
        this.getLocationByNameUseCase = getLocationByNameUseCase;
        this.getLocationsUseCase = getLocationsUseCase;
    }

    public void unsubscribeFromUseCases() {
        getLocationsUseCase.unsubscribe();
        getLocationByNameUseCase.unsubscribe();
    }

    @Override
    public void getLocations() {
        getLocationsUseCase.execute(this::onLocation, this::onError, this::onLocationEnd);
    }

    @Override
    public void queryLocationByName(String name) {
        getLocationByNameUseCase.setName(name).execute(this::onQuery, this::onError);
    }

    private void onError(Throwable throwable) {
        checkView();
        view.onError();
    }

    private void onLocation(Location location) {
        checkView();
        view.onLocationReceived(location);
    }

    private void onLocationEnd() {
        checkView();
        view.onLocationEnd();
    }

    private void onQuery(Location location) {
        checkView();
        view.onQueryReceived(location);
    }
}
