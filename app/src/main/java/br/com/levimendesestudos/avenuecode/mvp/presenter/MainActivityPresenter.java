package br.com.levimendesestudos.avenuecode.mvp.presenter;

import java.util.List;

import br.com.levimendesestudos.avenuecode.models.Address;
import br.com.levimendesestudos.avenuecode.mvp.contracts.MainActivityMVP;

/**
 * Created by 809778 on 09/08/2016.
 */
public class MainActivityPresenter implements MainActivityMVP.UserActions {

    private MainActivityMVP.View mView;

    public MainActivityPresenter(MainActivityMVP.View view) {
        mView = view;
    }

    @Override
    public List<Address> search() {


        return null;
    }
}
