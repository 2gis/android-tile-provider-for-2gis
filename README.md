# Android demo project for tile provider

Get your key for Maps Android API

```
https://developers.google.com/maps/documentation/android-api/signup
```

Enter yout key into AndroidManifest.xml
```
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="ENTER_YOUR_KEY" />
```

Add custom points
```
map.addMarker(new MarkerOptions().position(new LatLng(25.263831, 55.288396)).title("FUEL_STATION")).setTag(0);
map.addMarker(new MarkerOptions().position(new LatLng(25.260066, 55.285606)).title("HOTEL")).setTag(0);
map.addMarker(new MarkerOptions().position(new LatLng(25.24457, 55.318308)).title("HOSPITAL")).setTag(0);
```

Processing of a click on a point occurs in onMarkerClick function
```
public boolean onMarkerClick(final Marker marker) {
    Toast.makeText(this, marker.getTitle(), Toast.LENGTH_LONG).show();
    return false;
}
```
