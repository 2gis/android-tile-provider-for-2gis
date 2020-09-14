package com.example.tileprovider;


import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener, OnSeekBarChangeListener, OnMapReadyCallback {

    private static final String TILE_URL = "https://tile0.maps.2gis.com/tiles?x=%d&y=%d&z=%d&v=1";
    private static final int MIN_ZOOM = 2;
    private static final int MAX_ZOOM = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        TileProvider tileProvider = new UrlTileProvider(256, 256) {
            @Override
            public URL getTileUrl(int x, int y, int zoom) {
                String s = String.format(TILE_URL, x, y, zoom);

                if (zoom < MIN_ZOOM || zoom > MAX_ZOOM) {
                    return null;
                }

                try {
                    return new URL(s);
                } catch (Exception e) {
                    throw new AssertionError(e);
                }
            }
        };

        map.setMapType(GoogleMap.MAP_TYPE_NONE);
        map.addTileOverlay(new TileOverlayOptions().tileProvider(tileProvider));
        map.setOnMarkerClickListener(this);

        map.addMarker(new MarkerOptions().position(new LatLng(25.263831, 55.288396)).title("FUEL_STATION")).setTag(0);
        map.addMarker(new MarkerOptions().position(new LatLng(25.260066, 55.285606)).title("HOTEL")).setTag(0);
        map.addMarker(new MarkerOptions().position(new LatLng(25.24457, 55.318308)).title("HOSPITAL")).setTag(0);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_LONG).show();
        return false;
    }
}
