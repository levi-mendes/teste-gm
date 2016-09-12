package br.com.levimendesestudos.avenuecode;

import android.app.Application;
import android.content.Context;

/**
 * Created by 809778 on 18/04/2016.
 */
public class ApplicationAvenueCodeApp extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ApplicationAvenueCodeApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
}