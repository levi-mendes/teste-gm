package br.com.levimendesestudos.avenuecode.db;

import br.com.levimendesestudos.avenuecode.models.Address;
import io.realm.Realm;

/**
 * Created by 809778 on 09/08/2016.
 */
public class AddressDB {

    public boolean save(Address address) {
        Realm realm = Realm.getDefaultInstance();

        // Copy the object to Realm. Any further changes must happen on realmUser
        realm.beginTransaction();
        Address retorno = realm.copyToRealm(address);
        realm.commitTransaction();

        return retorno != null;
    }

    public void delete(Address pAddress) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        Address address = realm.where(Address.class)
                .equalTo("formattedAddress", pAddress.formattedAddress)
                .findFirst();

        address.deleteFromRealm();
        realm.commitTransaction();
    }

    public Address find(Address pAddress) {
        Realm realm = Realm.getDefaultInstance();

        return realm.where(Address.class)
                .equalTo("formattedAddress", pAddress.formattedAddress)
                .findFirst();
    }
}