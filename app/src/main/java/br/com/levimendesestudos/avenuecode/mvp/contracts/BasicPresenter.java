package br.com.levimendesestudos.avenuecode.mvp.contracts;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 809778 on 19/07/2017.
 */

public abstract class BasicPresenter {

    public abstract void init();

    public <T> void subscribe(Observable<T> obs, Observer<T> observer) {
        obs.observeOn(AndroidSchedulers.mainThread())
           .subscribeOn(Schedulers.io())
           .subscribe(observer);
    }
}
