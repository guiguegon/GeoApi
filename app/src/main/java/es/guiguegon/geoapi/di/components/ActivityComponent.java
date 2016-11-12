package es.guiguegon.geoapi.di.components;

import dagger.Component;
import es.guiguegon.geoapi.components.ui.BaseActivity;
import es.guiguegon.geoapi.di.modules.ActivityModule;
import es.guiguegon.geoapi.di.scopes.PerActivity;

/**
 * Created by guiguegon on 12/11/2016.
 */
@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);
}
