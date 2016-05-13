package be.howest.nmct.roeteplanner;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.Formatter;

import be.howest.nmct.roeteplanner.classes.Roete;

public class RoeteActivity extends Fragment implements OnMapReadyCallback {

    private static View view;
    private static Roete _roete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();

            if (parent != null)
                parent.removeView(view);
        }

        try {
            view = inflater.inflate(R.layout.activity_roete, container, false);
        }
        catch (InflateException e) {
            /* map is already there, just return view as it is */
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(RoeteActivity.this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        if (_roete != null && _roete.getAankomstLocatie() != null && _roete.getVertrekLocatie() != null) {
            LatLng aankomst = new LatLng(_roete.getAankomstLocatie().getLatitude(), _roete.getAankomstLocatie().getLongitude());
            googleMap.addMarker(new MarkerOptions().position(aankomst).title("aankomst"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(aankomst));

            LatLng vertrek = new LatLng(_roete.getVertrekLocatie().getLatitude(), _roete.getVertrekLocatie().getLongitude());
            googleMap.addMarker(new MarkerOptions().position(vertrek).title("vertrek"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(vertrek));

            googleMap.addPolygon(new PolygonOptions().add(aankomst, vertrek).fillColor(Color.BLUE).strokeWidth(2));
        }
    }

    public static Fragment newInstance() {
        return new RoeteActivity();
    }

    public static Fragment newInstance(Roete roete) {
        _roete = roete;
        return newInstance();
    }
}
