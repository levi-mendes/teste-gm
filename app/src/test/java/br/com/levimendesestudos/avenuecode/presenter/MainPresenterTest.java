package br.com.levimendesestudos.avenuecode.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.levimendesestudos.avenuecode.api.GoogleAPI;
import br.com.levimendesestudos.avenuecode.models.Address;
import br.com.levimendesestudos.avenuecode.mvp.contracts.MainMVP;
import br.com.levimendesestudos.avenuecode.mvp.presenter.MainPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by 809778 on 10/08/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private MainMVP.View mView;
    @Mock
    @Inject
    private GoogleAPI mGoogleAPI;

    MainPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mPresenter = new MainPresenter(mView);
    }

    @Test
    public void shouldCallLoadList() throws  Exception {
        when(mView.address()).thenReturn("Springfield");
        mPresenter.search();

        List<Address> list = mock(ArrayList.class);

        list.add(new Address("aaa", 0.1, 0.1));
        list.add(new Address("aaa", 0.1, 0.1));
        list.add(new Address("aaa", 0.1, 0.1));

        verify(mView).loadList(list);
    }

    @Test
    public void validateShouldReturnFalseWhenAddressIsEmpty() {
        when(mView.address()).thenReturn("");

        Assert.assertFalse(mPresenter.validate());
    }
}