package br.com.levimendesestudos.avenuecode.mvp.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.levimendesestudos.avenuecode.api.GoogleAPI;
import br.com.levimendesestudos.avenuecode.deserializer.AddressDeserializer;
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

        Gson gson = new GsonBuilder().registerTypeAdapter(List.class, new AddressDeserializer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GoogleAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mGoogleAPI = retrofit.create(GoogleAPI.class);
    }

    @Override
    public void search() {
        mView.showPbLoading();

        Map<String, String> params = new HashMap<>();
        params.put("address", mView.address());
        params.put("sensor", "false");

        mGoogleAPI.search(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Address>>(){

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
                    public void onNext(List<Address> result) {

                        if (result == null || result.size() == 0) {
                            mView.showNoResults();

                        } else {
                            mView.hideNoResults();
                        }

                        if (result != null && result.size() > 1) {
                            Address address = new Address();
                            address.formattedAddress = "Display All on Map";
                            result.add(0, address);
                        }

                        mView.loadList(result);
                    }
                });
    }
}
