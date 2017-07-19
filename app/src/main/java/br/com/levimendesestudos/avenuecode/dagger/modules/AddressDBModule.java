package br.com.levimendesestudos.avenuecode.dagger.modules;

import br.com.levimendesestudos.avenuecode.db.AddressDB;
import dagger.Module;
import dagger.Provides;

/**
 * Created by 809778 on 06/09/2016.
 */
@Module
public class AddressDBModule {

    @Provides
    public AddressDB providesAddressDB() {
        return new AddressDB();
    }
}
