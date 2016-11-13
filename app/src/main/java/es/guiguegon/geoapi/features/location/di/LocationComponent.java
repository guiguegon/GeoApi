package es.guiguegon.geoapi.features.location.di;

import dagger.Subcomponent;
import es.guiguegon.geoapi.di.modules.ActivityModule;
import es.guiguegon.geoapi.di.scopes.PerActivity;
import es.guiguegon.geoapi.features.location.LocationActivity;
import es.guiguegon.geoapi.features.location.LocationFragment;

/**
 * Created by guiguegon on 12/11/2016.
 */
@PerActivity
@Subcomponent(modules = {ActivityModule.class, LocationModule.class})
public interface LocationComponent {

    void inject(LocationActivity locationActivity);

    void inject(LocationFragment locationFragment);
}
