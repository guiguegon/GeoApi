package es.guiguegon.geoapi.features.location;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import es.guiguegon.geoapi.R;
import es.guiguegon.geoapi.components.base.BaseFragment;
import es.guiguegon.geoapi.features.location.di.LocationModule;
import es.guiguegon.geoapi.tools.NetworkManager;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class LocationFragment extends BaseFragment implements LocationContract.View {

    @Inject
    LocationPresenter locationPresenter;
    @Inject
    NetworkManager networkManager;

    public static LocationFragment newInstance() {
        return new LocationFragment();
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
        locationPresenter.setView(this);
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
}
