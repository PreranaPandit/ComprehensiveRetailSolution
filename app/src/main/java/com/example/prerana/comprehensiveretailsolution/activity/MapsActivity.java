package com.example.prerana.comprehensiveretailsolution.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.model.LatitudeLongitude;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private AutoCompleteTextView etCity;
    private Button btnSearch;
    private List<LatitudeLongitude> latitudeLongitudeList;
    Marker markerName;
    CameraUpdate center, zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        etCity = findViewById(R.id.etCitySearch);
        btnSearch = findViewById(R.id.btnSearchAddress);

        fillArrayListAndSetAdapter();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etCity.getText().toString()))
                {
                    etCity.setError("Please enter person name");
                    return;
                }

                //get the current location of the place
                int position = SearchArrayList(etCity.getText().toString());

                if(position > -1)
                {
                    loadMap(position);
                }
                else
                {
                    Toast.makeText(MapsActivity.this, "Location not found!.." + etCity.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void fillArrayListAndSetAdapter() {

        latitudeLongitudeList = new ArrayList<>();

        latitudeLongitudeList.add(new LatitudeLongitude( 27.6994312,85.2901898,"Avinash Joshi"));
        latitudeLongitudeList.add(new LatitudeLongitude(27.6733233,85.3356681 ,"Bikram Shahi"));
        latitudeLongitudeList.add(new LatitudeLongitude( 27.7358156,85.3306925,"Suhana Pandit"));
        latitudeLongitudeList.add(new LatitudeLongitude( 27.6733387,85.3355802,"Ram Sharma"));
        latitudeLongitudeList.add(new LatitudeLongitude(27.6766582, 85.288778,"RI Lights"));
        latitudeLongitudeList.add(new LatitudeLongitude(27.6984173,85.3483995, "Creative Store"));
        latitudeLongitudeList.add(new LatitudeLongitude( 27.7098166,85.3280037,"Himalayan Solution"));
        latitudeLongitudeList.add(new LatitudeLongitude( 27.6698424,85.3213712,"Dream Retails"));
        //latitudeLongitudeList.add(new LatitudeLongitude(27.9109275,84.8940755, "Red Cross Blood Bank, Nilkantha 45100"));

        String[] data = new String[latitudeLongitudeList.size()];

        for(int i =0; i < data.length;i++)
        {
            data[i] = latitudeLongitudeList.get(i).getMarker();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MapsActivity.this, android.R.layout.simple_list_item_1,data);

        etCity.setAdapter(adapter);
        etCity.setThreshold(1);
    }

    //this function will check wether the location is in list or not

    public int SearchArrayList(String name)
    {
        for (int i =0; i< latitudeLongitudeList.size();i++)
        {
            if(latitudeLongitudeList.get(i).getMarker().contains(name))
            {
                return  i;
            }
        }
        return  -1;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (etCity.isPerformingCompletion()){
            // Add a marker in Sydney and move the camera
            center = CameraUpdateFactory.newLatLng(new LatLng(27.7107553,83.4679022));
            zoom = CameraUpdateFactory.zoomTo(15);
            mMap.moveCamera(center);
            mMap.animateCamera(zoom);
            mMap.getUiSettings().setZoomControlsEnabled(true);

        }
        else {
            List<LatitudeLongitude> latLngs = new ArrayList<>();

            latLngs.add(new LatitudeLongitude(27.7080332,85.3353207, "All Goods Suppliers"));
            latLngs.add(new LatitudeLongitude(27.7048249,85.3136514, "Electricals Solution"));
            latLngs.add(new LatitudeLongitude(27.6841753,85.298451, "NortWest Store"));
            latLngs.add(new LatitudeLongitude(27.7025456,85.3201618, "Shivam Raut"));
            latLngs.add(new LatitudeLongitude(27.6899912, 85.3190591, "Amrit Tuladhar"));
            latLngs.add(new LatitudeLongitude(27.7183852,85.3464575, "Nobel Mechinary"));
            latLngs.add(new LatitudeLongitude(27.7359917,85.3302595, "Himal Store"));
            latLngs.add(new LatitudeLongitude(27.752876,85.325889, "Nepal Electic"));
            //  latLngs.add(new LatitudeLongitude(27.9109275,84.8940755, "Red Cross Blood Bank, Nilkantha 45100"));

            CameraUpdate center, zoom;

            for (int i = 0; i < latLngs.size(); i++) {
                center = CameraUpdateFactory.newLatLng(new LatLng(latLngs.get(i).getLat(),
                        latLngs.get(i).getLon()));

                zoom = CameraUpdateFactory.zoomTo(12);
                mMap.addMarker(new MarkerOptions().position(new LatLng(latLngs.get(i).getLat(),
                        latLngs.get(i).getLon())).title(latLngs.get(i).getMarker()));

                mMap.moveCamera(center);
                mMap.animateCamera(zoom);
                mMap.getUiSettings().setZoomControlsEnabled(true);

            }
        }



    }

    public void loadMap(int position)
    {
        //removes old marker from map
        if(markerName!=null)
        {
            markerName.remove();
        }

        double latitude = latitudeLongitudeList.get(position).getLat();
        double longitude = latitudeLongitudeList.get(position).getLon();
        String marker = latitudeLongitudeList.get(position).getMarker();

        center = CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude));

        zoom = CameraUpdateFactory.zoomTo(17);
        markerName = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(marker));

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }
}
