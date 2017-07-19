package br.com.levimendesestudos.avenuecode.dagger.components;

import javax.inject.Singleton;

import br.com.levimendesestudos.avenuecode.dagger.modules.AddressDBModule;
import br.com.levimendesestudos.avenuecode.dagger.modules.GoogleApiModule;
import br.com.levimendesestudos.avenuecode.mvp.presenter.MainPresenter;
import br.com.levimendesestudos.avenuecode.mvp.presenter.MapsPresenter;
import dagger.Component;

@Component(modules = {
        GoogleApiModule.class,
        AddressDBModule.class
})
@Singleton
public interface AppComponent {

    void inject(MainPresenter presenter);
    void inject(MapsPresenter presenter);
}