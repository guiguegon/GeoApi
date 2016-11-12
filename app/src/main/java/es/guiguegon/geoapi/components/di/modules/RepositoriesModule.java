package es.guiguegon.geoapi.components.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.guiguegon.geoapi.net.GeoService;
import es.guiguegon.geoapi.repositories.location.LocationDataRepository;
import es.guiguegon.geoapi.repositories.location.LocationRepository;
import retrofit2.Retrofit;

/**
 * Created by guiguegon on 12/11/2016.
 */

@Module
public class RepositoriesModule {

    @Provides
    @Singleton
    LocationRepository provideLocationRepository(LocationDataRepository locationDataRepository) {
        return locationDataRepository;
    }

    @Provides
    @Singleton
    GeoService provideGeoService(Retrofit retrofit) {
        return retrofit.create(GeoService.class);
    }
}
