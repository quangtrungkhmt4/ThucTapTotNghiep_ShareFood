package com.example.share_food;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.share_food.utils.SharedPref;
import com.example.share_food.utils.Utils;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.example.share_food.config.API.API_GET_CATEGORY;
import static com.example.share_food.config.KeyPref.LIST_CATEGORIES;

public class SplashScreenActivity extends AppCompatActivity {

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (!Utils.checkPermission(this) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            startActivity(new Intent(this, PermissionActivity.class));
            finish();
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }

        checkConnection();
    }

    protected boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private void checkConnection() {
        if (!isInternetAvailable()) {
            Toast.makeText(this, getString(R.string.check_your_connection), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            finish();
        } else {
            getListCategory();
        }
    }

    private void getListCategory() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(API_GET_CATEGORY, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response == null || response.length() == 0) {
                    checkConnection();
                } else {
                    SharedPref.saveData(LIST_CATEGORIES, response.toString(), SplashScreenActivity.this);
                    CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
//                            Map<String, Double> location = getLocation();
                            Map<String, Double> location = new HashMap<>();
                            location.put("lattitude", (double) 21.054386);
                            location.put("longitude", (double) 105.73511);
                            intent.putExtra("location", (Serializable) location);
                            startActivity(intent);
                            finish();
                        }
                    }.start();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SplashScreenActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private Map<String, Double> getLocation() {
        Map<String, Double> map = new HashMap<>();

        double latti;
        double longi;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            map.put("lattitude", (double) 21.054386);
            map.put("longitude", (double) 105.73511);
            return map;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

        if (location != null) {
            latti = location.getLatitude();
            longi = location.getLongitude();
        } else  if (location1 != null) {
            latti = location1.getLatitude();
            longi = location1.getLongitude();
        } else  if (location2 != null) {
            latti = location2.getLatitude();
            longi = location2.getLongitude();
        }else{
            latti = 21.054386;
            longi = 105.73511;
        }

        map.put("lattitude", latti);
        map.put("longitude", longi);
        return map;
    }
}
