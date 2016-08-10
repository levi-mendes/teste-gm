package br.com.levimendesestudos.avenuecode.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
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
    private LatLngBounds.Builder mBuilder = new LatLngBounds.Builder();
    private List<Address> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mPresenter = new MapActivityPresenter(this);

        mList      = (List<Address>)getIntent().getSerializableExtra("addresses");

        //flag that indicates if all or a sinlge address
        mAll       = getIntent().getBooleanExtra("all", false);
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
        zoom();

        //mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    private void zoom() {
        LatLngBounds bounds = mBuilder.build();

        /*int padding = 20; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.animateCamera(cu);*/

        // begin new code:
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.12); // offset from edges of the map 12% of screen

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
        // end of new code

        mMap.animateCamera(cu);
    }

    public void addMarker(Address address) {
        LatLng latLng = new LatLng(address.lati, address.longi);

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(address.toString());

        mMap.addMarker(markerOptions);

        mBuilder.include(latLng);
    }

    private void init() {
        if (!mAll) {
            //if a single item, we nees to get the selected position in the list
            int position = getIntent().getIntExtra("position", 1);
            mAddress = mList.get(position);
            addMarker(mAddress);
            return;
        }

        loadAll();
    }

    private void loadAll() {
        //add a marker for each item in the list
        for (int cont = 1; cont < mList.size(); cont++) {
            addMarker(mList.get(cont));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu not necessary in case of all items
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
        ConfirmationDF confirmationDF = ConfirmationDF.newInstance(R.string.warnning, R.string.are_you_sure, android.R.string.ok);

        confirmationDF.setOnDialogOptionClickListener(object -> {

            AddressDB db = new AddressDB(MapsActivity.this);
            boolean res = db.delete(mAddress);

            if (res) {
                ToastUtil.showShort(MapsActivity.this, R.string.item_deleted_from_table);
                finish();
            }
        });
        confirmationDF.show(getFragmentManager(), "confirmationDF");
    }

}
