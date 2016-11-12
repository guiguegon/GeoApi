package es.guiguegon.geoapi.di.modules;

import dagger.Module;
import dagger.Provides;
import es.guiguegon.geoapi.components.base.BaseActivity;
import es.guiguegon.geoapi.di.scopes.PerActivity;

/**
 * Created by guiguegon on 12/11/2016.
 */
@Module
public class ActivityModule {
    private final BaseActivity baseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    BaseActivity provideActivity() {
        return this.baseActivity;
    }
}