package es.guiguegon.geoapi.components.base;

import es.guiguegon.geoapi.tools.Navigator;

/**
 * Created by guiguegon on 12/11/2016.
 */

public class BasePresenter<T extends BaseContract.View> {

    protected T view;
    protected Navigator navigator;

    public BasePresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void clearView() {
        unsubscribeFromUseCases();
        view = null;
    }

    protected void checkView() {
        if (null == view) {
            throw new IllegalStateException("view not set");
        }
    }

    public void unsubscribeFromUseCases() {
    }
}
