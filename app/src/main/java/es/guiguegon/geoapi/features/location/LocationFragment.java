package es.guiguegon.geoapi.features.location;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import es.guiguegon.geoapi.R;
import es.guiguegon.geoapi.components.base.BaseFragment;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.data.models.Weather;
import es.guiguegon.geoapi.features.location.di.LocationModule;
import es.guiguegon.geoapi.tools.GoogleMapHelper;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class LocationFragment extends BaseFragment
        implements LocationContract.View, OnMapReadyCallback {

    private final static String ARGUMENT_LOCATION = "argument_location";

    @Inject
    LocationPresenter locationPresenter;
    @Inject
    GoogleMapHelper googleMapHelper;
    @BindView(R.id.location_name)
    TextView locationName;
    @BindView(R.id.location_latlng)
    TextView locationLatLng;

    private Location location;
    private List<Weather> weathers;

    public static LocationFragment newInstance(Location location) {
        LocationFragment fragment = new LocationFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_LOCATION, location);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.i("onViewCreated LocationFragment");
        locationPresenter.setView(this);
        if (savedInstanceState == null) {
            location = getArguments().getParcelable(ARGUMENT_LOCATION);
            if (null == location) {
                throw new IllegalStateException("location null");
            }
            setUpLocation();
            locationPresenter.storeLocation(location);
            locationPresenter.getWeatherFromLocation(location);
        }
    }

    private void setUpMap() {
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.location_map);
        mapFragment.getMapAsync(this);
    }

    private void setUpLocation() {
        locationName.setText(location.getName());
        locationLatLng.setText(location.getLat() + "," + location.getLng());
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        getComponent().with(new LocationModule()).inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        locationPresenter.clearView();
    }

    @Override
    public void onError() {
        Snackbar.make(getView(), "An error occurred", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onWeatherReceived(List<Weather> weathers) {
        this.weathers = weathers;
        locationPresenter.storeWeather(location, weathers);
        setUpMap();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMapHelper.onMapReady(googleMap);
        googleMapHelper.setWeathers(weathers);
    }
}
