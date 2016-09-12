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
import br.com.levimendesestudos.avenuecode.models.Address;
import br.com.levimendesestudos.avenuecode.mvp.contracts.MapsMVP;
import br.com.levimendesestudos.avenuecode.mvp.presenter.MapsActivityPresenter;
import br.com.levimendesestudos.avenuecode.utils.ConfirmationDF;
import br.com.levimendesestudos.avenuecode.utils.ToastUtil;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback, MapsMVP.View {

    private GoogleMap mMap;
    private Address mAddress;
    private LatLngBounds.Builder mBuilder = new LatLngBounds.Builder();
    private List<Address> mList;

    private MapsActivityPresenter mPresenter;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mList      = (List<Address>)getIntent().getSerializableExtra("addresses");
        //flag that indicates if all or a sinlge address

        mPresenter = new MapsActivityPresenter(this);
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

        //after map is ready...

        mPresenter.init();
        //mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    @Override
    public void showToast(int res) {
        ToastUtil.showShort(this, getString(res));
    }

    @Override
    public Address address() {
        int position = getIntent().getIntExtra("position", 1);
        mAddress = mList.get(position);

        return mAddress;
    }

    @Override
    public void zoom() {
        LatLngBounds bounds = mBuilder.build();

        int width   = getResources().getDisplayMetrics().widthPixels;
        int height  = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.12); // offset from edges of the map 12% of screen

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
        mMap.animateCamera(cu);
    }

    @Override
    public void addMarker(Address address) {
        LatLng latLng = new LatLng(address.lati, address.longi);

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(address.toString());

        mMap.addMarker(markerOptions);

        mBuilder.include(latLng);
    }

    @Override
    public void loadSingle() {
        //if a single item, we nees to get the selected position in the list
        int position = getIntent().getIntExtra("position", 1);
        mAddress = mList.get(position);
        addMarker(mAddress);
    }

    @Override
    public void loadAll() {
        //add a marker for each item in the list
        for (int cont = 1; cont < mList.size(); cont++) {
            addMarker(mList.get(cont));
        }
    }

    @Override
    public boolean all() {
        return getIntent().getBooleanExtra("all", false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        mPresenter.createMenu();

        return true;
    }


    @Override
    public void menuSave() {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.maps_menu, mMenu);

        mMenu.findItem(R.id.itemSave).setVisible(true);
        mMenu.findItem(R.id.itemDelete).setVisible(false);
    }

    @Override
    public void menuDelete() {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.maps_menu, mMenu);

        mMenu.findItem(R.id.itemSave).setVisible(false);
        mMenu.findItem(R.id.itemDelete).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mPresenter.itemSelected(item.getItemId());

        return true;
    }


    @Override
    public void confirmationDelete() {
        ConfirmationDF confirmationDF = ConfirmationDF.newInstance(R.string.warnning, R.string.are_you_sure, android.R.string.ok);
        confirmationDF.setOnDialogOptionClickListener(object -> mPresenter.delete());
        confirmationDF.show(getFragmentManager(), "confirmationDF");
    }

}
