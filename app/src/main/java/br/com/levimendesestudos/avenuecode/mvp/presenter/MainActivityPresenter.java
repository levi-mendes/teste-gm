package br.com.levimendesestudos.avenuecode.mvp.presenter;

import android.util.Log;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import br.com.levimendesestudos.avenuecode.api.GoogleAPI;
import br.com.levimendesestudos.avenuecode.dagger.DaggerInjector;
import br.com.levimendesestudos.avenuecode.models.Address;
import br.com.levimendesestudos.avenuecode.mvp.contracts.MainActivityMVP;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 809778 on 09/08/2016.
 */
public class MainActivityPresenter implements MainActivityMVP.UserActions {

    private MainActivityMVP.View mView;

    @Inject
    GoogleAPI mGoogleAPI;

    public MainActivityPresenter(MainActivityMVP.View view) {
        mView = view;

        DaggerInjector.get().inject(this);
    }

    @Override
    public void search() {
        mView.hideKeyboard();
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
                        return;
                    }

                    addShowDisplayAll(result);
                    mView.hideNoResults();
                    mView.loadList(result);
                }
            });
    }

    private void addShowDisplayAll(List<Address> list) {
        if (list.size() > 1) {
            Address address = new Address("Display All on Map", 0.0, 0.0);
            list.add(0, address);
        }
    }

}
