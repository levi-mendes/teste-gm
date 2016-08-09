package br.com.levimendesestudos.avenuecode.mvp.contracts;

import java.util.List;

import br.com.levimendesestudos.avenuecode.models.Address;

/**
 * Created by 809778 on 09/08/2016.
 */
public interface MainActivityMVP {

    //Presenter should implement this interface
    interface UserActions {
        List<Address> search();
    }

    //UI should implement this interface
    interface View {
        String address();
        void loadList(List<Address> list);
        void showPbLoading();
        void hidePbLoading();
    }
}
