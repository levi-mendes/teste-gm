package br.com.levimendesestudos.avenuecode.mvp.presenter;

import android.util.Log;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import br.com.levimendesestudos.avenuecode.R;
import br.com.levimendesestudos.avenuecode.api.GoogleAPI;
import br.com.levimendesestudos.avenuecode.dagger.DaggerInjector;
import br.com.levimendesestudos.avenuecode.models.Address;
import br.com.levimendesestudos.avenuecode.mvp.contracts.MainMVP;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 809778 on 09/08/2016.
 */
public class MainPresenter implements MainMVP.Presenter {

    private MainMVP.View mView;

    @Inject
    GoogleAPI mGoogleAPI;

    public MainPresenter(MainMVP.View view) {
        mView = view;

        DaggerInjector.get().inject(this);
    }

    @Override
    public void init() {

    }

    /**
     *
     * @return
     */
    public boolean validate() {
        if (mView.address().isEmpty()) {
            mView.setErrorAddress(true, mView.getString(R.string.type_an_address));
            return false;
        }

        mView.setErrorAddress(false, null);

        return true;
    }

    @Override
    public void search() {
        if (!validate()) {
            return;
        }

        mView.hideKeyboard();
        mView.showPbLoading();

        mGoogleAPI.search(params())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(handleResultSearch());
    }

    private Subscriber<List<Address>> handleResultSearch() {
        return new Subscriber<List<Address>>(){
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
                    mView.cleanList();
                    return;
                }

                addShowDisplayAll(result);
                mView.hideNoResults();
                mView.loadList(result);
            }
        };
    }

    private Map<String, String> params() {
        Map<String, String> params = new HashMap<>();
        params.put("address", mView.address());
        params.put("sensor", "false");

        return params;
    }

    /**
     *
     * only will add "Display All on Map", if returned more than one item
     *
     * @param list
     *
     */
    private void addShowDisplayAll(List<Address> list) {
        if (list.size() > 1) {
            Address address = new Address(mView.getString(R.string.display_all_on_map), 0.0, 0.0);
            list.add(0, address);
        }
    }
}