package es.guiguegon.geoapi.features.location;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import es.guiguegon.geoapi.R;
import es.guiguegon.geoapi.components.base.BaseActivity;
import es.guiguegon.geoapi.features.location.di.LocationModule;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class LocationActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, LocationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setUi();
        injectDependencies();
        if (null == savedInstanceState) {
            loadLocationFragment();
        }
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        getComponent().with(new LocationModule()).inject(this);
    }

    private void loadLocationFragment() {
        loadFragment(this, R.id.content_location, LocationFragment.newInstance());
    }

    private void setUi() {
        setSupportActionBar(toolbar);
    }
}