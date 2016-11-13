package es.guiguegon.geoapi.features.location;

import es.guiguegon.geoapi.components.base.BasePresenter;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.models.Weather;
import es.guiguegon.geoapi.data.usecases.location.StoreLocationUseCase;
import es.guiguegon.geoapi.data.usecases.weather.GetWeatherByLocationUseCase;
import es.guiguegon.geoapi.data.usecases.weather.StoreWeatherUseCase;
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
    private StoreWeatherUseCase storeWeatherUseCase;
    private GetWeatherByLocationUseCase getWeatherByLocationUseCase;

    @Inject
    public LocationPresenter(StoreLocationUseCase storeLocationUseCase,
            GetWeatherByLocationUseCase getWeatherByLocationUseCase,
            StoreWeatherUseCase storeWeatherUseCase, Navigator navigator) {
        super(navigator);
        this.storeLocationUseCase = storeLocationUseCase;
        this.getWeatherByLocationUseCase = getWeatherByLocationUseCase;
        this.storeWeatherUseCase = storeWeatherUseCase;
    }

    public void unsubscribeFromUseCases() {
        storeLocationUseCase.unsubscribe();
        getWeatherByLocationUseCase.unsubscribe();
        storeWeatherUseCase.unsubscribe();
    }

    public void storeLocation(Location location) {
        storeLocationUseCase.setLocation(location).execute(this::onStoredLocation, this::onError);
    }

    public void storeWeather(Location location, List<Weather> weather) {
        storeWeatherUseCase.setLocation(location)
                .setWeathers(weather)
                .execute(this::onStoredWeather, this::onError);
    }

    public void getWeatherFromLocation(Location location) {
        getWeatherByLocationUseCase.setLocation(location).execute(this::onWeather, this::onError);
    }

    private void onStoredWeather(Boolean value) {
        Timber.i("onStored Weather");
    }

    private void onStoredLocation(Boolean value) {
        Timber.i("onStored Location");
    }

    private void onWeather(List<Weather> weathers) {
        checkView();
        view.onWeatherReceived(weathers);
    }

    private void onError(Throwable throwable) {
        checkView();
        view.onError();
    }
}
