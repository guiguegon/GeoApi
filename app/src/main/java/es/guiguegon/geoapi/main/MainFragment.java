package es.guiguegon.geoapi.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import es.guiguegon.geoapi.R;
import es.guiguegon.geoapi.components.ui.BaseFragment;
import es.guiguegon.geoapi.main.di.MainModule;
import javax.inject.Inject;

/**
 * Created by Guille on 12/11/2016.
 */

public class MainFragment extends BaseFragment implements MainContract.View {

    @Inject
    MainPresenter mainPresenter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainPresenter.setView(this);
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        getComponent().with(new MainModule()).inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainPresenter.clearView();
    }
}
