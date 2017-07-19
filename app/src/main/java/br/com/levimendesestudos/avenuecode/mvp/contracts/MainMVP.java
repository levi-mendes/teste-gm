package br.com.levimendesestudos.avenuecode.mvp.contracts;

import java.util.List;

import br.com.levimendesestudos.avenuecode.models.Address;

/**
 * Created by 809778 on 09/08/2016.
 */
public interface MainMVP {

    //Presenter should implement this interface
    abstract class Presenter extends BasicPresenter {

        public abstract void search();
    }

    //UI should implement this interface
    interface View extends BasicView {

        String address();

        void loadList(List<Address> list);

        void showPbLoading();

        void hidePbLoading();

        void showNoResults();

        void hideNoResults();

        void hideKeyboard();

        void cleanList();

        void setErrorAddress(boolean value, String resId);

    }
}
