package br.com.levimendesestudos.avenuecode.dagger;

public class DaggerInjector {

    //private static AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
    private static AppComponent appComponent = DaggerAppComponent.builder().googleApiModule(new GoogleApiModule()).build();

    public static AppComponent get() {
        return appComponent;
    }
}
