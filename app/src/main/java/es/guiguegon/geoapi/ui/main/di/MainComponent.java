package es.guiguegon.geoapi.ui.main.di;

import dagger.Subcomponent;
import es.guiguegon.geoapi.di.modules.ActivityModule;
import es.guiguegon.geoapi.di.scopes.PerActivity;
import es.guiguegon.geoapi.ui.main.MainActivity;
import es.guiguegon.geoapi.ui.main.MainFragment;
import es.guiguegon.geoapi.ui.main.MainPresenter;

/**
 * Created by guiguegon on 12/11/2016.
 */
@PerActivity
@Subcomponent(modules = { ActivityModule.class, MainModule.class })
public interface MainComponent {

    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);

    void inject(MainPresenter mainPresenter);
}
