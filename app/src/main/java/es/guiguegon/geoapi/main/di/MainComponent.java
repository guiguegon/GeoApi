package es.guiguegon.geoapi.main.di;

import dagger.Subcomponent;
import es.guiguegon.geoapi.components.di.modules.ActivityModule;
import es.guiguegon.geoapi.components.di.scopes.PerActivity;
import es.guiguegon.geoapi.main.MainActivity;
import es.guiguegon.geoapi.main.MainFragment;
import es.guiguegon.geoapi.main.MainPresenter;

/**
 * Created by Guille on 12/11/2016.
 */
@PerActivity
@Subcomponent(modules = { ActivityModule.class, MainModule.class })
public interface MainComponent {

    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);

    void inject(MainPresenter mainPresenter);
}
