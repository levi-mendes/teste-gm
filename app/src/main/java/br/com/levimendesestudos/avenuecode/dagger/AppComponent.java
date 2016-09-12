package br.com.levimendesestudos.avenuecode.dagger;

import javax.inject.Singleton;

import br.com.levimendesestudos.avenuecode.db.AddressDB;
import br.com.levimendesestudos.avenuecode.mvp.presenter.MainActivityPresenter;
import br.com.levimendesestudos.avenuecode.mvp.presenter.MapsActivityPresenter;
import dagger.Component;

@Component(modules = {
        GoogleApiModule.class,
        AddressDBModule.class
})
@Singleton
public interface AppComponent {

    void inject(MainActivityPresenter presenter);
    void inject(MapsActivityPresenter presenter);
}