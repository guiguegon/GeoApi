package es.guiguegon.geoapi.components.ui;

import es.guiguegon.geoapi.tools.Navigator;

/**
 * Created by Guille on 12/11/2016.
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
        view = null;
    }

    private void checkView() {
        if (view == null) {
            throw new IllegalStateException("view not set");
        }
    }
}
