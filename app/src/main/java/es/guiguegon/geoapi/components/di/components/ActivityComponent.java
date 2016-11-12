package es.guiguegon.geoapi.components.di.components;

import dagger.Component;
import es.guiguegon.geoapi.components.di.modules.ActivityModule;
import es.guiguegon.geoapi.components.di.scopes.PerActivity;
import es.guiguegon.geoapi.components.ui.BaseActivity;
import es.guiguegon.geoapi.components.ui.BaseFragment;
import es.guiguegon.geoapi.main.di.MainComponent;
import es.guiguegon.geoapi.main.di.MainModule;

/**
 * Created by guiguegon on 12/11/2016.
 */
@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);

    MainComponent with(MainModule mainModule);
}
