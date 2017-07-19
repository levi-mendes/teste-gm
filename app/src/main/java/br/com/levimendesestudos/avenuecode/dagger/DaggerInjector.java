package br.com.levimendesestudos.avenuecode.dagger;

import br.com.levimendesestudos.avenuecode.dagger.components.AppComponent;
import br.com.levimendesestudos.avenuecode.dagger.modules.GoogleApiModule;

public class DaggerInjector {

    //private static AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
    private static AppComponent appComponent = DaggerAppComponent.builder().googleApiModule(new GoogleApiModule()).build();

    public static AppComponent get() {
        return appComponent;
    }
}
