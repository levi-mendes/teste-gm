package br.com.levimendesestudos.avenuecode.mvp.contracts;

import br.com.levimendesestudos.avenuecode.models.Address;

/**
 * Created by 809778 on 09/08/2016.
 */
public interface MapsActivityMVP {

    //Presenter should implement this interface
    interface UserActions {

    }

    //UI should implement this interface
    interface View {
        void addMarker(Address address);
    }
}
