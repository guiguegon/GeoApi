package es.guiguegon.geoapi.components.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.guiguegon.geoapi.di.components.ActivityComponent;

/**
 * Created by guiguegon on 12/11/2016.
 */

public abstract class BaseFragment extends Fragment {

    Unbinder unbinder;
    ActivityComponent activityComponent;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        injectDependencies();
    }

    protected void injectDependencies() {
        activityComponent = ((BaseActivity) getActivity()).getComponent();
        activityComponent.inject(this);
    }

    protected ActivityComponent getComponent() {
        return activityComponent;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
