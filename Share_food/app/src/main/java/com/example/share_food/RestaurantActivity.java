package com.example.share_food;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.share_food.adapters.RestaurantAdapter;
import com.example.share_food.config.API;
import com.example.share_food.model.Restaurant;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class RestaurantActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private GridView gvRes;
    private RestaurantAdapter restaurantAdapter;
    private List<Restaurant> restaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        findID();
        initViews();
        loadData();
    }

    private void findID() {
        toolbar = findViewById(R.id.toolbarRestaurant);
        gvRes = findViewById(R.id.gvRestaurant);
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Danh sách nhà hàng");
        }

        restaurantAdapter = new RestaurantAdapter(this, R.layout.item_category, restaurants);
        gvRes.setAdapter(restaurantAdapter);
        gvRes.setOnItemClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        Intent intent = getIntent();
        final int idFood = intent.getIntExtra("idFood", -1);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.API_GET_RESTAURANT_FROM_FOOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("null")){
                    Toast.makeText(RestaurantActivity.this, "Phát sinh lỗi không xác định.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(response.equalsIgnoreCase("[]")){
                    Toast.makeText(RestaurantActivity.this, "Không có nhà hàng nào có món ăn này.", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) parser.parse(response);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        Restaurant restaurant = gson.fromJson(jsonArray.get(i), Restaurant.class);
                        restaurants.add(restaurant);
                    }
                    restaurantAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("food", String.valueOf(idFood));
                return params;
            }
        };

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.API_GET_RESTAURANT_FROM_FOOD, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.equalsIgnoreCase("null")){
//                    Toast.makeText(RestaurantActivity.this, "Phát sinh lỗi không xác định.", Toast.LENGTH_SHORT).show();
//                    return;
//                }else if(response.equalsIgnoreCase("[]")){
//                    Toast.makeText(RestaurantActivity.this, "Không có nhà hàng nào có món ăn này.", Toast.LENGTH_SHORT).show();
//                    return;
//                }else {
//                    Gson gson = new Gson();
//                    JsonParser parser = new JsonParser();
//                    JsonArray jsonArray = (JsonArray) parser.parse(response);
//                    for (int i=0; i<jsonArray.size(); i++){
//                        Restaurant restaurant = gson.fromJson(jsonArray.get(i), Restaurant.class);
//                        .add(food);
//                    }
//                    foodAdapter.notifyDataSetChanged();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("username", user);
//                params.put("password", pass);
//                return params;
//            }
//        };
//
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String latLong = restaurants.get(position).getLatLong();
        String[] strings = latLong.split("<>");
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.parseFloat(strings[0]), Float.parseFloat(strings[1]));
        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent2);
    }
}
