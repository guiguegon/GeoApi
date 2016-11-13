package es.guiguegon.geoapi.tools;

import android.content.Context;
import android.graphics.Color;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import es.guiguegon.geoapi.data.models.Weather;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by guiguegon on 13/11/2016
 */
public class GoogleMapHelper implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback {

    private final static int MAP_PADDING = 200;

    private Context context;
    private GoogleMap mMap;
    private List<Weather> weathers;
    private ClusterManager<Weather> clusterManager;
    private IconGenerator iconFactory;

    private boolean isLoaded;

    @Inject
    public GoogleMapHelper(Context context) {
        this.context = context;
        iconFactory = new IconGenerator(context);
        weathers = new ArrayList<>();
    }

    public boolean isMapLoaded() {
        return mMap != null && isLoaded;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Timber.i("[onMapReady]");
        mMap = googleMap;
        setupMap();
        refreshMap();
    }

    @Override
    public void onMapLoaded() {
        Timber.i("[onMapLoaded]");
        isLoaded = true;
    }

    private void setupMap() {
        Timber.i("[setupMap]");
        try {
            mMap.getUiSettings().setMapToolbarEnabled(false);
            mMap.getUiSettings().setCompassEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mMap.setOnMapLoadedCallback(this);
            setUpClusterer();
        } catch (Exception e) {
            Timber.wtf(e, "[setupMap]");
        }
    }

    private void setUpClusterer() {
        Timber.i("[setUpClusterer]");
        // Initialize the manager with the context and the map.
        clusterManager = new ClusterManager<>(context, mMap);
        mMap.setOnMarkerClickListener(clusterManager);
        clusterManager.setRenderer(
                new DefaultClusterRenderer<Weather>(context, mMap, clusterManager) {
                    @Override
                    protected boolean shouldRenderAsCluster(Cluster cluster) {
                        return false;
                    }

                    @Override
                    protected void onBeforeClusterItemRendered(Weather item,
                            MarkerOptions markerOptions) {
                        double temperature = Double.parseDouble(item.getTemperature());
                        setIconColor(temperature);
                        markerOptions.
                                icon(BitmapDescriptorFactory.fromBitmap(
                                        iconFactory.makeIcon(item.getTemperature()))).
                                position(item.getPosition()).
                                anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
                    }
                });
    }

    private void setIconColor(double temperature) {
        if (temperature < 10 && temperature >= 1) {
            iconFactory.setColor(Color.CYAN);
        } else if (temperature < 15 && temperature >= 10) {
            iconFactory.setColor(Color.GREEN);
        } else if (temperature < 21 && temperature >= 15) {
            iconFactory.setColor(Color.BLUE);
        } else if (temperature < 26 && temperature >= 21) {
            iconFactory.setColor(Color.YELLOW);
        } else if (temperature >= 26) {
            iconFactory.setColor(Color.RED);
        }
    }

    private void refreshMap() {
        Timber.i("[refreshMap]");
        clusterManager.clearItems();
        clusterManager.cluster();
        clusterManager.addItems(weathers);
        clusterManager.cluster();
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
        refreshMap();
        animateCamera(generateLatLngBoundsFromMarkers());
    }

    private LatLngBounds generateLatLngBoundsFromMarkers() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Weather weather : weathers) {
            builder.include(weather.getPosition());
        }
        return builder.build();
    }

    public void animateCamera(LatLngBounds latLngBounds) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, MAP_PADDING));
    }
}