package br.com.levimendesestudos.avenuecode.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import br.com.levimendesestudos.avenuecode.R;

/**
 * Created by 809778 on 03/10/2016.
 */

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private final View mContents;

    public CustomInfoWindowAdapter(Activity activity) {
        mWindow   = activity.getLayoutInflater().inflate(R.layout.custom_info_window, null);
        mContents = activity.getLayoutInflater().inflate(R.layout.custom_info_contents, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        render(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        render(marker, mContents);
        return mContents;
    }

    private void render(Marker marker, View view) {
        ((ImageView) view.findViewById(R.id.badge)).setImageResource(R.drawable.google_maps_icon);
        String title = marker.getTitle();
        TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
        snippetUi.setText(title);
    }
}