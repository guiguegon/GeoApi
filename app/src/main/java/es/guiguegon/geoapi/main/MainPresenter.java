package es.guiguegon.geoapi.main;

import es.guiguegon.geoapi.components.di.scopes.PerActivity;
import es.guiguegon.geoapi.components.ui.BasePresenter;
import es.guiguegon.geoapi.models.Location;
import es.guiguegon.geoapi.tools.Navigator;
import es.guiguegon.geoapi.usecases.location.GetLocationByNameUseCase;
import es.guiguegon.geoapi.usecases.location.GetLocationsUseCase;
import javax.inject.Inject;

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
        getLocationsUseCase.execute(this::onLocation, this::onError);
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

    private void onQuery(Location location) {
        checkView();
        view.onQueryReceived(location);
    }
}
