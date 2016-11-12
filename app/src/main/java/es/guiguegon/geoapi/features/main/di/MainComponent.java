package es.guiguegon.geoapi.features.main.di;

import dagger.Subcomponent;
import es.guiguegon.geoapi.di.modules.ActivityModule;
import es.guiguegon.geoapi.di.scopes.PerActivity;
import es.guiguegon.geoapi.features.main.MainActivity;
import es.guiguegon.geoapi.features.main.MainFragment;

/**
 * Created by guiguegon on 12/11/2016.
 */
@PerActivity
@Subcomponent(modules = { ActivityModule.class, MainModule.class })
public interface MainComponent {

    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);
}
