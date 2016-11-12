package es.guiguegon.geoapi.components.base;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.guiguegon.geoapi.tools.Navigator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BasePresenterTest {

    @Mock
    BaseContract.View view;
    @Mock
    Navigator navigator;
    private BasePresenter<BaseContract.View> basePresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.basePresenter = new BasePresenter<BaseContract.View>(navigator);
    }

    @Test
    public void test_setView() throws Exception {
        basePresenter.setView(view);
        assertEquals(basePresenter.view, view);
    }

    @Test
    public void test_clearView() throws Exception {
        basePresenter.clearView();
        assertNull(basePresenter.view);
    }

    @Test(expected = IllegalStateException.class)
    public void test_checkView_fail() throws Exception {
        basePresenter.clearView();
        basePresenter.checkView();
    }
}