package com.example.hikers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    LocationManager locationManager;

    LocationListener locationListener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location)
            {

                updatelocationinfo(location);

                Log.i("Location", location.toString());

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }else
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10,locationListener);
            Location lastknownlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (lastknownlocation != null)
            {
                updatelocationinfo(lastknownlocation);
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if(grantResults.length >0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            startlistning();
        }

    }

    public void startlistning()
    {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, locationListener);
        }
    }


    public void updatelocationinfo(Location location)
    {
        Log.i("Location", location.toString());

        TextView lattextview = (TextView) findViewById(R.id.lattextView3);
        TextView longtextview = (TextView) findViewById(R.id.longtextView4);
        TextView alttextview = (TextView) findViewById(R.id.alttextView2);
        TextView acctview = (TextView) findViewById(R.id.acctextView5);
        TextView addressview = (TextView) findViewById(R.id.addresstextView6);


        lattextview.setText("Latitude : "+ Double.toString(location.getLatitude()));
        longtextview.setText("Longitude : "+ Double.toString(location.getLongitude()));
        acctview.setText("Accuracy : "+ Double.toString(location.getAccuracy()));
        alttextview.setText("Altitude : "+ Double.toString(location.getAltitude()));

        String address= "Could'nt find address";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> listaddress= geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

            if(listaddress!= null && listaddress.size()>0)
            {
                address = "Address : \n";


                if(listaddress.get(0).getThoroughfare() != null)
                {
                    address += listaddress.get(0).getThoroughfare() + "\n";
                }

                if(listaddress.get(0).getLocality() != null)
                {
                    address += listaddress.get(0).getLocality() + " ";
                }

                if(listaddress.get(0).getPostalCode() != null)
                {
                    address += listaddress.get(0).getPostalCode() + " ";
                }

                if(listaddress.get(0).getAdminArea() != null)
                {
                    address += listaddress.get(0).getAdminArea() + " ";
                }

            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        addressview.setText(address);

    }
}
