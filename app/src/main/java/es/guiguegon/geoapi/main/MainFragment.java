package es.guiguegon.geoapi.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import es.guiguegon.geoapi.R;
import es.guiguegon.geoapi.components.ui.BaseFragment;
import es.guiguegon.geoapi.exceptions.NetworkConnectionException;
import es.guiguegon.geoapi.main.adapters.LocationAdapter;
import es.guiguegon.geoapi.main.di.MainModule;
import es.guiguegon.geoapi.models.Location;
import es.guiguegon.geoapi.tools.NetworkManager;
import es.guiguegon.geoapi.tools.Utils;
import timber.log.Timber;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class MainFragment extends BaseFragment implements MainContract.View {

    @Inject
    MainPresenter mainPresenter;
    @Inject
    NetworkManager networkManager;

    @BindView(R.id.main_search_view)
    EditText mainEditText;
    @BindView(R.id.main_recycler_view)
    RecyclerView mainRecyclerView;
    @BindView(R.id.main_empty_view)
    LinearLayout mainEmptyView;

    private LocationAdapter locationAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        setUpAdapter();
        getLocationList();
    }

    private void setUpAdapter() {
        locationAdapter = new LocationAdapter();
        mainRecyclerView.setAdapter(locationAdapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void getLocationList() {
        mainPresenter.getLocations();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                queryLocation();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void queryLocation() {
        try {
            String name = Utils.toString(mainEditText);
            if (Utils.isNotBlank(name)) {
                mainEditText.setError(null);
                networkManager.checkConnectivity(getContext());
                mainPresenter.queryLocationByName(name);
                locationAdapter.clear();
            } else {
                mainEditText.setError(getString(R.string.main_location_query_empty));
            }
        } catch (NetworkConnectionException e) {
            Timber.e(e, "NetworkConnectionException");
        }
    }

    @Override
    public void onError() {
        Snackbar.make(getView(), "An error occurred", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onLocationReceived(Location location) {
        locationAdapter.addLocation(location);
    }

    @Override
    public void onLocationEnd() {
        if (locationAdapter.isEmpty()) {
            mainEmptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onQueryReceived(Location location) {
        locationAdapter.addLocation(location);
    }
}
