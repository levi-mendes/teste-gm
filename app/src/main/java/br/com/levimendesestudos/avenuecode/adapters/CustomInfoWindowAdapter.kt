package br.com.levimendesestudos.avenuecode.adapters

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import br.com.levimendesestudos.avenuecode.R

/**
 * Created by 809778 on 03/10/2016.
 */

class CustomInfoWindowAdapter(var activity: Activity) : GoogleMap.InfoWindowAdapter {


    override fun getInfoWindow(marker: Marker): View {
        val window = activity.layoutInflater.inflate(R.layout.custom_info_window, null)
        render(marker, window)
        return window
    }

    override fun getInfoContents(marker: Marker): View {
        val contents = activity.layoutInflater.inflate(R.layout.custom_info_contents, null)

        render(marker, contents)
        return contents
    }

    private fun render(marker: Marker, view: View) {
        (view.findViewById<View>(R.id.badge) as ImageView).setImageResource(R.drawable.google_maps_icon)
        val title = marker.title
        val snippetUi = view.findViewById<View>(R.id.snippet) as TextView
        snippetUi.text = title
    }
}