package br.com.levimendesestudos.avenuecode.dagger;

import javax.inject.Singleton;

import br.com.levimendesestudos.avenuecode.mvp.presenter.MainActivityPresenter;
import dagger.Component;

@Component(modules = {
        GoogleApiModule.class
})
@Singleton
public interface AppComponent {

    void inject(MainActivityPresenter presenter);
    //void inject(MainActivityPresenterTest test);
}