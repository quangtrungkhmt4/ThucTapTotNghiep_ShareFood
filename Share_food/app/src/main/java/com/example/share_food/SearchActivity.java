package com.example.share_food;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.share_food.adapters.FoodAdapter;
import com.example.share_food.config.API;
import com.example.share_food.model.Food;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {

    private SearchView searchView;
    private Toolbar toolbar;
    private ListView lvFood;
    private TextView tvEmpty;
    private FoodAdapter foodAdapter;
    private List<Food> foods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        findID();
        initViews();
    }

    private void findID() {
        toolbar = findViewById(R.id.toolbarSearch);
        searchView = findViewById(R.id.search_view);
        lvFood = findViewById(R.id.lvFoodSearch);
        tvEmpty = findViewById(R.id.tvEmptySearch);
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
            Objects.requireNonNull(getSupportActionBar()).setTitle("Tìm kiếm");
        }
        searchView.setOnQueryTextListener(this);
        foodAdapter = new FoodAdapter(this, R.layout.item_listview_food, foods);
        lvFood.setAdapter(foodAdapter);
        lvFood.setEmptyView(tvEmpty);
        lvFood.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        search(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void search(final String key){
        foods.clear();
        foodAdapter.notifyDataSetChanged();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.API_GET_SEARCH_FOOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("null")){
                    Toast.makeText(SearchActivity.this, "Phát sinh lỗi không xác định.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(response.equalsIgnoreCase("[]")){
                    Toast.makeText(SearchActivity.this, "Không có kết quà nào phù hợp với từ khóa của bạn.", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) parser.parse(response);
                    for (int i=0; i<jsonArray.size(); i++){
                        Food food = gson.fromJson(jsonArray.get(i), Food.class);
                        foods.add(food);
                    }
                    foodAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("key", key);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Food currentFood = foods.get(position);
        Intent intent = new Intent(this, FoodInfoActivity.class);
        intent.putExtra("food", currentFood);
        startActivity(intent);
    }
}
