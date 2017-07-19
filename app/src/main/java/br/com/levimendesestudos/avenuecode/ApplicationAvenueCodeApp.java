package br.com.levimendesestudos.avenuecode;

import android.app.Application;
import io.realm.Realm;

/**
 * Created by 809778 on 18/04/2016.
 */
public class ApplicationAvenueCodeApp extends Application {

    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}