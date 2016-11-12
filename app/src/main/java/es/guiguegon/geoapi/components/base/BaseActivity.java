package es.guiguegon.geoapi.components.base;

import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import es.guiguegon.geoapi.components.app.AndroidApplication;
import es.guiguegon.geoapi.di.components.DaggerFeatureComponent;
import es.guiguegon.geoapi.di.components.FeatureComponent;
import es.guiguegon.geoapi.di.modules.ActivityModule;

/**
 * Created by guiguegon on 12/11/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    FeatureComponent featureComponent;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected void injectDependencies() {
        featureComponent = DaggerFeatureComponent.builder()
                .applicationComponent(
                        ((AndroidApplication) (getApplicationContext())).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
        featureComponent.inject(this);
    }

    protected FeatureComponent getComponent() {
        return featureComponent;
    }

    private FragmentTransaction getFragmentTransaction(FragmentActivity activity,
            int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction =
                activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, fragment.getClass().getSimpleName());
        return fragmentTransaction;
    }

    protected void loadFragment(FragmentActivity activity, int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction =
                getFragmentTransaction(activity, containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected void loadFragmentAddToBackStack(FragmentActivity activity, int containerViewId,
            Fragment fragment) {
        FragmentTransaction fragmentTransaction =
                getFragmentTransaction(activity, containerViewId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
