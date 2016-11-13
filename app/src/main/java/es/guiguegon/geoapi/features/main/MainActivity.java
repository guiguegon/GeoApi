package es.guiguegon.geoapi.features.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import es.guiguegon.geoapi.R;
import es.guiguegon.geoapi.components.base.BaseActivity;
import es.guiguegon.geoapi.features.main.di.MainModule;
import timber.log.Timber;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate MainActivity");
        setContentView(R.layout.activity_main);
        setUi();
        injectDependencies();
        if (null == savedInstanceState) {
            loadMainFragment();
        }
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        getComponent().with(new MainModule()).inject(this);
    }

    private void loadMainFragment() {
        loadFragment(this, R.id.content_main, MainFragment.newInstance());
    }

    private void setUi() {
        setSupportActionBar(toolbar);
    }
}