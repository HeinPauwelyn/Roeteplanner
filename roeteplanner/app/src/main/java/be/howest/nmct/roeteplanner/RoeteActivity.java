package be.howest.nmct.roeteplanner;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.PolygonOptions;

import be.howest.nmct.roeteplanner.classes.Route;

public class RoeteActivity extends Fragment implements OnMapReadyCallback {

    private static View view;
    private static Route _route;

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


        if (_route != null && _route.getAankomstLocatie() != null && _route.getVertrekLocatie() != null) {
            LatLng aankomst = new LatLng(_route.getAankomstLocatie().getLatitude(), _route.getAankomstLocatie().getLongitude());
            googleMap.addMarker(new MarkerOptions().position(aankomst).title("aankomst"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(aankomst));

            LatLng vertrek = new LatLng(_route.getVertrekLocatie().getLatitude(), _route.getVertrekLocatie().getLongitude());
            googleMap.addMarker(new MarkerOptions().position(vertrek).title("vertrek"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(vertrek));

            googleMap.addPolygon(new PolygonOptions().add(aankomst, vertrek).fillColor(Color.BLUE).strokeWidth(2));
        }
    }

    public static Fragment newInstance() {
        return new RoeteActivity();
    }

    public static Fragment newInstance(Route route) {
        _route = route;
        return newInstance();
    }
}
