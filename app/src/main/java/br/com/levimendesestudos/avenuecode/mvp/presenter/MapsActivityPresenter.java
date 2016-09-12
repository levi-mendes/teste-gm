package br.com.levimendesestudos.avenuecode.mvp.presenter;

import javax.inject.Inject;
import br.com.levimendesestudos.avenuecode.R;
import br.com.levimendesestudos.avenuecode.dagger.DaggerInjector;
import br.com.levimendesestudos.avenuecode.db.AddressDB;
import br.com.levimendesestudos.avenuecode.models.Address;
import br.com.levimendesestudos.avenuecode.mvp.contracts.MapsMVP;

/**
 * Created by 809778 on 10/08/2016.
 */
public class MapsActivityPresenter implements MapsMVP.Presenter {

    private MapsMVP.View mView;

    @Inject
    AddressDB mAddressDB;

    private Address mAddress;

    public MapsActivityPresenter(MapsMVP.View view) {
        mView = view;

        DaggerInjector.get().inject(this);
        mAddress = mView.address();
    }

    @Override
    public void init() {
        if (mView.all()) {
            mView.loadAll();

        } else {
            mView.loadSingle();
        }

        //make a zoom after load marker(s)
        mView.zoom();
    }

    @Override
    public void itemSelected(int item) {
        switch (item) {
            case R.id.itemSave:
                save();
                break;

            case R.id.itemDelete:
                mView.confirmationDelete();
                break;
        }
    }

    @Override
    public void save() {
        boolean res = mAddressDB.save(mAddress);

        if (res) {
            mView.showToast(R.string.item_saved);
            mView.finish();
        }
    }

    @Override
    public void delete() {
        boolean res = mAddressDB.delete(mAddress);

        if (res) {
            mView.showToast(R.string.item_deleted_from_table);
            mView.finish();
        }
    }

    @Override
    public void createMenu() {
        //menu not necessary in case of all items
        if (mView.all()) {
            return;
        }

        Address address = mAddressDB.find(mAddress.formattedAddress);

        if (address == null) {
            mView.menuSave();

        } else {
            mView.menuDelete();
        }
    }
}
