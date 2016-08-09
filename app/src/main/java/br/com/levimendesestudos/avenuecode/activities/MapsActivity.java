package br.com.levimendesestudos.avenuecode.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.levimendesestudos.avenuecode.R;
import br.com.levimendesestudos.avenuecode.db.AddressDB;
import br.com.levimendesestudos.avenuecode.models.Address;
import br.com.levimendesestudos.avenuecode.mvp.contracts.MapsActivityMVP;
import br.com.levimendesestudos.avenuecode.mvp.presenter.MapActivityPresenter;
import br.com.levimendesestudos.avenuecode.utils.ConfirmationDF;
import br.com.levimendesestudos.avenuecode.utils.ToastUtil;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback, MapsActivityMVP.View {

    private GoogleMap mMap;
    private MapActivityPresenter mPresenter;
    private Address mAddress;
    private boolean mAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mPresenter = new MapActivityPresenter(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        init();
        //mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    public void addMarker(Address address) {
        LatLng latLng = new LatLng(address.lati, address.longi);

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(address.toString());

        mMap.addMarker(markerOptions);
    }

    private void init() {
        int position = getIntent().getIntExtra("position", 1);
        mAll = getIntent().getBooleanExtra("all", false);

        List<Address> list = (List<Address>)getIntent().getSerializableExtra("addresses");

        if (!mAll) {
            mAddress = list.get(position);
            addMarker(mAddress);
            return;
        }

        for (int cont = 1; cont < list.size(); cont++) {
            addMarker(list.get(cont));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mAll) {
            return false;
        }

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.maps_menu, menu);

        AddressDB db = new AddressDB(this);
        Address address = db.find(mAddress.formattedAddress);

        if (address == null) {
            menu.findItem(R.id.itemSave).setVisible(true);
            menu.findItem(R.id.itemDelete).setVisible(false);

        } else {
            menu.findItem(R.id.itemSave).setVisible(false);
            menu.findItem(R.id.itemDelete).setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSave:
                save();
                break;

            case R.id.itemDelete:
                delete();
                break;
        }
        return true;
    }

    private void save() {
        AddressDB db = new AddressDB(MapsActivity.this);
        boolean res = db.save(mAddress);

        if (res) {
            ToastUtil.showShort(this, getString(R.string.item_saved));
            finish();
        }
    }

    private void delete() {
        ConfirmationDF confirmationDF = ConfirmationDF.newInstance(R.string.warnning, R.string.do_you_confirm_deletion, android.R.string.ok);

        confirmationDF.setOnDialogOptionClickListener(new ConfirmationDF.OnDialogOptionClickListener() {
            @Override
            public void onDialogOptionPressed(Object object) {
                AddressDB db = new AddressDB(MapsActivity.this);
                boolean res = db.delete(mAddress);

                if (res) {
                    ToastUtil.showShort(MapsActivity.this, R.string.item_deleted_from_table);
                    finish();
                }

            }
        });
        confirmationDF.show(getFragmentManager(), "confirmationDF");
    }

}
