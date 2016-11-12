package es.guiguegon.geoapi.components.di.naming;
/**
 * Created by guiguegon on 12/11/2016.
 */

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Local {

    /** The name. */
    String value() default "local";
}
