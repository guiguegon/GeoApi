package es.guiguegon.geoapi.components.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.guiguegon.geoapi.di.components.FeatureComponent;

/**
 * Created by guiguegon on 12/11/2016.
 */

public abstract class BaseFragment extends Fragment {

    Unbinder unbinder;
    FeatureComponent featureComponent;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        injectDependencies();
    }

    protected void injectDependencies() {
        featureComponent = ((BaseActivity) getActivity()).getComponent();
        featureComponent.inject(this);
    }

    protected FeatureComponent getComponent() {
        return featureComponent;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
