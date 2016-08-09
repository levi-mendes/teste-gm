package br.com.levimendesestudos.avenuecode.mvp.presenter;

import br.com.levimendesestudos.avenuecode.mvp.contracts.MapsActivityMVP;

/**
 * Created by 809778 on 09/08/2016.
 */
public class MapActivityPresenter implements MapsActivityMVP.UserActions {

    private MapsActivityMVP.View mView;

    public MapActivityPresenter(MapsActivityMVP.View view) {
        mView = view;
    }


}
