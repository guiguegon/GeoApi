package es.guiguegon.geoapi.features.location;

import es.guiguegon.geoapi.components.base.BasePresenter;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.models.Weather;
import es.guiguegon.geoapi.data.usecases.location.StoreLocationUseCase;
import es.guiguegon.geoapi.data.usecases.weather.GetWeatherByLocationUseCase;
import es.guiguegon.geoapi.di.scopes.PerActivity;
import es.guiguegon.geoapi.tools.Navigator;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by guiguegon on 12/11/2016.
 */

@PerActivity
public class LocationPresenter extends BasePresenter<LocationContract.View>
        implements LocationContract.ActionListener {

    private StoreLocationUseCase storeLocationUseCase;
    private GetWeatherByLocationUseCase getWeatherByLocationUseCase;

    @Inject
    public LocationPresenter(StoreLocationUseCase storeLocationUseCase,
            GetWeatherByLocationUseCase getWeatherByLocationUseCase, Navigator navigator) {
        super(navigator);
        this.storeLocationUseCase = storeLocationUseCase;
        this.getWeatherByLocationUseCase = getWeatherByLocationUseCase;
    }

    public void unsubscribeFromUseCases() {
        storeLocationUseCase.unsubscribe();
        getWeatherByLocationUseCase.unsubscribe();
    }

    public void storeLocation(Location location) {
        storeLocationUseCase.setLocation(location).execute(this::onStored, this::onError);
    }

    public void getWeatherFromLocation(Location location) {
        getWeatherByLocationUseCase.setLocation(location).execute(this::onWeather, this::onError);
    }

    private void onError(Throwable throwable) {
        checkView();
        view.onError();
    }

    private void onStored(Boolean value) {
        Timber.i("onStored Location");
    }

    private void onWeather(List<Weather> weathers) {
        checkView();
        view.onWeatherReceived(weathers);
    }
}
