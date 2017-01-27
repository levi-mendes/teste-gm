package br.com.levimendesestudos.avenuecode.utils;

import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

import com.google.android.gms.maps.model.Marker;

/**
 * Created by 809778 on 27/01/2017.
 */

public class MarkerUtil {

    public static void animateWhenClicked(Marker marker) {
        // This causes the marker at Perth to bounce into position when it is clicked.
        Handler handler = new Handler();
        long start = SystemClock.uptimeMillis();
        long duration = 1500;

        final Interpolator interpolator = new BounceInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = Math.max(1 - interpolator.getInterpolation((float) elapsed / duration), 0);
                marker.setAnchor(0.5f, 1.0f + 2 * t);

                if (t > 0.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                }
            }
        });
    }
}
