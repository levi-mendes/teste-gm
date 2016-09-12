package br.com.levimendesestudos.avenuecode.dagger;

import br.com.levimendesestudos.avenuecode.ApplicationAvenueCodeApp;
import br.com.levimendesestudos.avenuecode.db.AddressDB;
import dagger.Module;
import dagger.Provides;

/**
 * Created by 809778 on 06/09/2016.
 */
@Module
public class AddressDBModule {

    @Provides
    AddressDB providesAddressDB() {
        return new AddressDB(ApplicationAvenueCodeApp.getAppContext());
    }
}
