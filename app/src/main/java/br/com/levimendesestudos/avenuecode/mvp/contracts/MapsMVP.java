package br.com.levimendesestudos.avenuecode.mvp.contracts;

import br.com.levimendesestudos.avenuecode.models.Address;

/**
 * Created by 809778 on 10/08/2016.
 */
public interface MapsMVP {

    interface View extends BasicView {

        void zoom();

        void addMarker(Address address);

        boolean all();

        void finish();

        Address address();

        void loadAll();

        void loadSingle();

        void showMenuSave();

        void showMenuDelete();

        void confirmationDelete();

    }

    abstract class Presenter extends BasicPresenter {

        public abstract void itemSelected(int item);

        public abstract void save();

        public abstract void delete();

        public abstract void createMenu();
    }
}