package br.com.levimendesestudos.avenuecode.mvp.presenter;

import android.util.Log;

import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.levimendesestudos.avenuecode.api.GoogleAPI;
import br.com.levimendesestudos.avenuecode.models.Address;
import br.com.levimendesestudos.avenuecode.mvp.contracts.MainActivityMVP;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 809778 on 09/08/2016.
 */
public class MainActivityPresenter implements MainActivityMVP.UserActions {

    private MainActivityMVP.View mView;

    GoogleAPI mGoogleAPI;

    public MainActivityPresenter(MainActivityMVP.View view) {
        mView = view;

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(GoogleAPI.BASE_URL)
                //.client(providesOkHttoClient())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .build();

        mGoogleAPI = retrofit.create(GoogleAPI.class);
    }

    @Override
    public List<Address> search() {
        mView.showPbLoading();

        Map<String, String> params = new HashMap<>();
        params.put("address", mView.address());

        mGoogleAPI.search(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>(){

                    @Override
                    public void onCompleted() {
                        mView.hidePbLoading();
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        unsubscribe();
                        Log.e("onError", e.getMessage(), e);
                    }

                    @Override
                    public void onNext(String result) {
                        Log.e("onNext", result);
                    }
                });


        return null;
    }
}
