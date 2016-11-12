package es.guiguegon.geoapi.components.ui;

import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import es.guiguegon.geoapi.components.app.AndroidApplication;
import es.guiguegon.geoapi.components.di.components.ActivityComponent;
import es.guiguegon.geoapi.components.di.components.DaggerActivityComponent;
import es.guiguegon.geoapi.components.di.modules.ActivityModule;

/**
 * Created by guiguegon on 12/11/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    ActivityComponent activityComponent;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected void injectDependencies() {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(
                        ((AndroidApplication) (getApplicationContext())).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
        activityComponent.inject(this);
    }

    protected ActivityComponent getComponent() {
        return activityComponent;
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
