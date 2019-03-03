package com.example.share_food;

import android.content.Intent;
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
import com.example.share_food.adapters.CategoryAdapter;
import com.example.share_food.config.API;
import com.example.share_food.config.KeyPref;
import com.example.share_food.model.Category;
import com.example.share_food.utils.SharedPref;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CategoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private GridView gvCategory;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        findID();
        initViews();
        loadData();
    }


    private void findID() {
        toolbar = findViewById(R.id.toolbarCate);
        gvCategory = findViewById(R.id.gvCategory);
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
            Objects.requireNonNull(getSupportActionBar()).setTitle("Danh sách loại món ăn");
        }

        categoryAdapter = new CategoryAdapter(this, R.layout.item_category, categories);
        gvCategory.setAdapter(categoryAdapter);
        gvCategory.setOnItemClickListener(this);
    }

    private void loadData() {
        String categoryString = SharedPref.getData(KeyPref.LIST_CATEGORIES, this);
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(categoryString).getAsJsonArray();
        for (int i=0; i<jsonArray.size(); i++){
            Category category = gson.fromJson(jsonArray.get(i), Category.class);
            categories.add(category);
        }
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.gvCategory:
                final String idCate = String.valueOf(categories.get(position).getId());

                StringRequest stringRequest = new StringRequest(Request.Method.POST, API.API_GET_FOOD_FROM_CATEGORY, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("[]")){
                            Toast.makeText(CategoryActivity.this, "Không có món ăn nào trong danh mục này.", Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            Intent intent = new Intent();
                            intent.putExtra("foods", response);
                            setResult(2, intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CategoryActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("category", idCate);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

                break;
        }
    }
}
