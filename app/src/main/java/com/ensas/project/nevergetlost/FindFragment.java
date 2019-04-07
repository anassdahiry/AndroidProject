package com.ensas.project.nevergetlost;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private static final String TAG = "FindFragment";
    Spinner spinner;
    Button btn_search,btn_mylocation;
    private static final int LOCATION_REQUEST_CODE = 101;
    private static final float DEFAULT_ZOOM = 11f;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_find, container, false);

        spinner = (Spinner) view.findViewById(R.id.tagListe);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btn_search = (Button) view.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);

        btn_mylocation = (Button) view.findViewById(R.id.btn_mylocation);
        btn_mylocation.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMap();
    }

    private void initMap() {
        Log.d(TAG, " ******** initMap: initializing map ********");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment contentFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.searchMap);
        contentFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng safi = new LatLng(32.326904, -9.263632);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(safi, 11f));
    }

    @Override
    public void onClick(View v) {
        if (v == btn_search){
            if(spinner.getSelectedItem().equals("Ecole")){
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.327037, -9.263686)).title("ENSA"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.308263, -9.216578)).title("EST"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.328635, -9.267904)).title("FAC"));
            }
            else if (spinner.getSelectedItem().equals("Hotel")){
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.323848, -9.255477)).title("Hotel Panorama"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.294611, -9.238770)).title("Hotel Atlantide"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.295999, -9.238738)).title("Hotel Farah"));
            }
            else if (spinner.getSelectedItem().equals("Snack")){
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.294059, -9.233664)).title("Taco"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.293596, -9.233385)).title("Restaurant Garibaldi"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.307376, -9.228577)).title("Pizzeria 07 "));
            }
            else if (spinner.getSelectedItem().equals("Magazins")){
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.293465, -9.234794)).title("DeFacto"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.293137, -9.234668)).title("Diamantine"));
            }
            else if (spinner.getSelectedItem().equals("Cafes")){
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.299240, -9.231723)).title("La Loge"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.299574, -9.231198)).title("Rosa Bianca"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.324971, -9.258858)).title("Cafe Ocean"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.293299, -9.234605)).title("Venezia Ice"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.293478, -9.235123)).title("Cafe La Flamme"));
            }
            else if (spinner.getSelectedItem().equals("SuperMarche")){
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.297894, -9.215561)).title("Marjane"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.293078, -9.234481)).title("Carrefour"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.295141, -9.227020)).title("Bim"));
            }
            else if (spinner.getSelectedItem().equals("Banques")){
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.293808, -9.234560)).title("CIH"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.289290, -9.241610)).title("BARID AL_MAGHRIB"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(32.313871, -9.229826)).title("ATTIJARIWAFA BANK"));
            }
        }
        if (v == btn_mylocation){
            try{
                mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {

                    requestPermissions(new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
                    return;
                }
                turnGPSOn();
            }
            catch (Exception ex){
                Toast.makeText(getContext(), "you have to enable GPS", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    turnGPSOn();
                } else
                    Toast.makeText(getContext(), "you don't have permission", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        try{
            final Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Log.d(TAG, "onComplete: found location!");
                        Location currentLocation = (Location) task.getResult();
                        moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM);
                        mMap.setMyLocationEnabled(true);
                        mMap.getUiSettings().setMyLocationButtonEnabled(false);
                    }
                    else{
                        Log.d(TAG, "onComplete: current location is null");
                        Toast.makeText(getContext(), "unable to get current location", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    private void turnGPSOn(){
        final LocationManager manager = (LocationManager) getActivity().getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }
        else{
            getDeviceLocation();
        }
    }

    private void buildAlertMessageNoGps(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
