package br.com.levimendesestudos.avenuecode.mvp.contracts;

import br.com.levimendesestudos.avenuecode.models.Address;

/**
 * Created by 809778 on 10/08/2016.
 */
public interface MapsMVP {

    interface View {
        void zoom();
        void addMarker(Address address);
        boolean all();
        void showToast(int res);
        void finish();
        Address address();
        void loadAll();
        void loadSingle();
        void menuSave();
        void menuDelete();
        void confirmationDelete();
    }

    interface Presenter {
        void init();
        void itemSelected(int item);
        void save();
        void delete();
        void createMenu();
    }
}
