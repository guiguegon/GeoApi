package es.guiguegon.geoapi.features.location;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import es.guiguegon.geoapi.R;
import es.guiguegon.geoapi.components.base.BaseActivity;
import es.guiguegon.geoapi.data.models.Location;
import es.guiguegon.geoapi.features.location.di.LocationModule;
import timber.log.Timber;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class LocationActivity extends BaseActivity {

    private final static String EXTRA_LOCATION = "extra_location";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent getCallingIntent(Context context, Location location) {
        Intent intent = new Intent(context, LocationActivity.class);
        intent.putExtra(EXTRA_LOCATION, location);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate LocationActivity");
        setContentView(R.layout.activity_location);
        setUi();
        injectDependencies();
        if (null == savedInstanceState) {
            Location location = getIntent().getParcelableExtra(EXTRA_LOCATION);
            if (null != location) {
                loadLocationFragment(location);
            } else {
                throw new IllegalStateException("location null");
            }
        }
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        getComponent().with(new LocationModule()).inject(this);
    }

    private void loadLocationFragment(Location location) {
        loadFragment(this, R.id.content_location, LocationFragment.newInstance(location));
    }

    private void setUi() {
        setSupportActionBar(toolbar);
    }
}
