package br.com.levimendesestudos.avenuecode.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.levimendesestudos.avenuecode.R;
import br.com.levimendesestudos.avenuecode.models.Address;
import br.com.levimendesestudos.avenuecode.mvp.contracts.MapsActivityMVP;
import br.com.levimendesestudos.avenuecode.mvp.presenter.MapActivityPresenter;
import br.com.levimendesestudos.avenuecode.utils.ToastUtil;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback, MapsActivityMVP.View {

    private GoogleMap mMap;
    private MapActivityPresenter mPresenter;

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

        addMarkers();
        //mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    public void addMarkers() {
        List<Address> addresses = (List<Address>)getIntent().getSerializableExtra("addresses");

        for (int cont = 0; cont < addresses.size(); cont++) {
            Address address = addresses.get(cont);
            //Marker marker = mMap.addMarker(new MarkerOptions().position(address.latLng).title(address.formattedAddress));
            mMap.addMarker(new MarkerOptions().position(new LatLng(address.lati, address.longi)).title(address.toString()));
            //animateMarker(marker);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.maps_menu, menu);
        //menu.findItem(R.id.itemSave).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSave:
                ToastUtil.showShort(this, "Menu Item 1 selected");
                break;

            case R.id.itemDelete:
                ToastUtil.showShort(this, "Menu item 2 selected");
                break;
        }
        return true;
    }

}
