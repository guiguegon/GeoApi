package es.guiguegon.geoapi.components.di.modules;

import dagger.Module;
import dagger.Provides;
import es.guiguegon.geoapi.components.di.scopes.PerActivity;
import es.guiguegon.geoapi.components.ui.BaseActivity;

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