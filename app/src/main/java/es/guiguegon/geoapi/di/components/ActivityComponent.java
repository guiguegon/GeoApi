package es.guiguegon.geoapi.di.components;

import dagger.Component;
import es.guiguegon.geoapi.components.base.BaseActivity;
import es.guiguegon.geoapi.components.base.BaseFragment;
import es.guiguegon.geoapi.di.modules.ActivityModule;
import es.guiguegon.geoapi.di.scopes.PerActivity;
import es.guiguegon.geoapi.ui.main.di.MainComponent;
import es.guiguegon.geoapi.ui.main.di.MainModule;

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
