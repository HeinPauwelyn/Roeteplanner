package be.howest.nmct.roeteplanner;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import be.howest.nmct.roeteplanner.classes.Roete;

public class RoeteActivity extends FragmentActivity implements OnMapReadyCallback {

    private static Roete _roete;
    private GoogleMap _map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roete);

        _roete = (Roete) getIntent().getSerializableExtra("roete");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        _map = googleMap;

        LatLng aankomst = new LatLng(_roete.getAankomstLocatie().getLatitude(), _roete.getAankomstLocatie().getLongitude());
        _map.addMarker(new MarkerOptions().position(aankomst).title("aankomst"));
        _map.moveCamera(CameraUpdateFactory.newLatLng(aankomst));

        LatLng vertrek = new LatLng(_roete.getVertrekLocatie().getLatitude(), _roete.getVertrekLocatie().getLongitude());
        _map.addMarker(new MarkerOptions().position(vertrek).title("vertrek"));
        _map.moveCamera(CameraUpdateFactory.newLatLng(vertrek));
    }
}
