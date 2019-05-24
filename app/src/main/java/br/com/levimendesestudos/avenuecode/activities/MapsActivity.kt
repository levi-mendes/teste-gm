package br.com.levimendesestudos.avenuecode.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import br.com.levimendesestudos.avenuecode.R
import br.com.levimendesestudos.avenuecode.adapters.CustomInfoWindowAdapter
import br.com.levimendesestudos.avenuecode.models.Address
import br.com.levimendesestudos.avenuecode.mvp.contracts.MapsMVP
import br.com.levimendesestudos.avenuecode.mvp.presenter.MapsPresenter
import br.com.levimendesestudos.avenuecode.utils.ConfirmationDF
import br.com.levimendesestudos.avenuecode.utils.MarkerUtil

class MapsActivity : BaseActivity(), OnMapReadyCallback, MapsMVP.View, GoogleMap.OnMarkerClickListener {

    private var mMap: GoogleMap? = null
    private var mAddress: Address? = null
    private val mBuilder = LatLngBounds.Builder()
    private var mList: List<Address>? = null

    private var mPresenter: MapsPresenter? = null
    private var mMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mList = intent.getParcelableArrayListExtra("addresses")
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        MarkerUtil.animateWhenClicked(marker)

        return false
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mPresenter = MapsPresenter(this)
        mPresenter!!.init()

        mMap!!.setOnMarkerClickListener(this)
        mMap!!.setInfoWindowAdapter(CustomInfoWindowAdapter(this))
    }

    override fun address(): Address? {
        val position = intent.getIntExtra("position", 1)
        mAddress = mList!![position]

        return mAddress
    }

    override fun zoom() {
        val bounds = mBuilder.build()

        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        val padding = (width * 0.12).toInt() // offset from edges of the map 12% of screen

        val cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)
        mMap!!.animateCamera(cu)
    }

    override fun addMarker(address: Address?) {
        val latLng = LatLng(address!!.lati, address.longi)

        val markerOptions = MarkerOptions()
                .position(latLng)
                .title(address.toString())

        mMap!!.addMarker(markerOptions)

        mBuilder.include(latLng)
    }

    override fun loadSingle() {
        //if a single item, we nees to get the selected position in the list
        val position = intent.getIntExtra("position", 1)
        mAddress = mList!![position]
        addMarker(mAddress)
    }

    override fun loadAll() {
        //add a marker for each item in the list
        for (item in mList!!) {
            addMarker(item)
        }
    }

    override fun all(): Boolean {
        return intent.getBooleanExtra("all", false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        mMenu = menu
        mPresenter!!.createMenu()

        return true
    }

    override fun showMenuSave() {
        val inflater = menuInflater
        inflater.inflate(R.menu.maps_menu, mMenu)

        mMenu!!.findItem(R.id.itemSave).isVisible = true
        mMenu!!.findItem(R.id.itemDelete).isVisible = false
    }

    override fun showMenuDelete() {
        val inflater = menuInflater
        inflater.inflate(R.menu.maps_menu, mMenu)

        mMenu!!.findItem(R.id.itemSave).isVisible = false
        mMenu!!.findItem(R.id.itemDelete).isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mPresenter!!.itemSelected(item.itemId)

        return true
    }

    override fun confirmationDelete() {
        val confirmationDF = ConfirmationDF.newInstance<Any>(R.string.warnning, R.string.are_you_sure, android.R.string.ok)
        confirmationDF.setOnDialogOptionClickListener { `object` -> mPresenter!!.delete() }
        confirmationDF.show(fragmentManager, "confirmationDF")
    }
}