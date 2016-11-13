package es.guiguegon.geoapi.features.main;

import android.app.Activity;
import es.guiguegon.geoapi.components.base.BasePresenter;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.usecases.location.DeleteLocationUseCase;
import es.guiguegon.geoapi.data.usecases.location.GetLocationByNameUseCase;
import es.guiguegon.geoapi.data.usecases.location.GetLocationsUseCase;
import es.guiguegon.geoapi.di.scopes.PerActivity;
import es.guiguegon.geoapi.tools.Navigator;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by guiguegon on 12/11/2016.
 */

@PerActivity
public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.ActionListener {

    private GetLocationByNameUseCase getLocationByNameUseCase;
    private GetLocationsUseCase getLocationsUseCase;
    private DeleteLocationUseCase deleteLocationUseCase;

    @Inject
    public MainPresenter(GetLocationByNameUseCase getLocationByNameUseCase,
            GetLocationsUseCase getLocationsUseCase, DeleteLocationUseCase deleteLocationUseCase,
            Navigator navigator) {
        super(navigator);
        this.getLocationByNameUseCase = getLocationByNameUseCase;
        this.getLocationsUseCase = getLocationsUseCase;
        this.deleteLocationUseCase = deleteLocationUseCase;
    }

    public void unsubscribeFromUseCases() {
        getLocationsUseCase.unsubscribe();
        getLocationByNameUseCase.unsubscribe();
        deleteLocationUseCase.unsubscribe();
    }

    @Override
    public void getLocations() {
        getLocationsUseCase.execute(this::onLocation, this::onError, this::onLocationComplete);
    }

    @Override
    public void queryLocationByName(String name) {
        getLocationByNameUseCase.setName(name)
                .execute(this::onQuery, this::onError, this::onQueryComplete);
    }

    @Override
    public void deleteLocation(Location location) {
        deleteLocationUseCase.setLocation(location).execute(this::onLocationDelete, this::onError);
    }

    @Override
    public void navigateToLocation(Activity activity, Location location, int requestCode) {
        navigator.navigateToLocation(activity, location, requestCode);
    }

    private void onError(Throwable throwable) {
        Timber.wtf(throwable, "onError");
        checkView();
        view.onError();
    }

    private void onLocation(Location location) {
        Timber.i("onLocation %s", location.toString());
        checkView();
        view.onLocationReceived(location);
    }

    private void onLocationComplete() {
        Timber.i("onLocationComplete");
        checkView();
        view.onLocationComplete();
    }

    private void onQuery(Location location) {
        Timber.i("onQuery %s", location);
        checkView();
        view.onQueryReceived(location);
    }

    private void onQueryComplete() {
        Timber.i("onQueryComplete");
        checkView();
        view.onQueryComplete();
    }

    private void onLocationDelete(Boolean value) {
        Timber.i("onLocationDelete %s", value);
    }
}
