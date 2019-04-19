package com.example.sharefood;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sharefood.adapter.PriceAdapter;
import com.example.sharefood.constant.API;
import com.example.sharefood.constant.Key;
import com.example.sharefood.model.Price;
import com.example.sharefood.util.Preferences;
import com.example.sharefood.util.ProcessDialog;
import com.example.sharefood.util.VolleySingleton;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener, PriceAdapter.OnClickItemListener {

    private RecyclerView recyclerViewFood;
    private PriceAdapter foodAdapter;
    private List<Price> foods = new ArrayList<>();
    private RequestQueue requestQueue;
    private ShimmerFrameLayout shimmerFrameLayout;
    private int allFoods = 0;
    private int currentPage = 1;
    private Toolbar toolbar;
    private ImageView imMenu;
    private Dialog dialog;
    private ProcessDialog processDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findId();
        initViews();
    }

    private void findId() {
        toolbar = findViewById(R.id.toolbarMain);
        recyclerViewFood = findViewById(R.id.recyclerFood);
        shimmerFrameLayout = findViewById(R.id.shimmerFood);
        imMenu = findViewById(R.id.imMenu);
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        requestQueue = VolleySingleton.getInstance(this).getmRequestQueue();

        recyclerViewFood.setLayoutManager(new LinearLayoutManager(this));
        foodAdapter = new PriceAdapter(recyclerViewFood, this, foods);
        recyclerViewFood.setAdapter(foodAdapter);
        foodAdapter.setOnItemClickListener(this);
        shimmerFrameLayout.startShimmer();
        processDialog = new ProcessDialog(this);
        countAllFood();

        loadMoreData(currentPage);

        //Set Load more event
        foodAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if (foods.size() <= allFoods) // Change max size
                {
                    foods.add(null);
                    foodAdapter.notifyItemInserted(foods.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            foods.remove(foods.size() - 1);
                            foodAdapter.notifyItemRemoved(foods.size());
                            currentPage++;
                            loadMoreData(currentPage);

                        }
                    }, 3000); // Time to load
                } else {
                    Toast.makeText(Main2Activity.this, "Load data completed !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imMenu.setOnClickListener(this);
    }

    private void loadMoreData(final int page) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API.PRICE_WITH_PAGE + "?page=" + page, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("code") == 0) {
                        JSONArray jsonArray = response.getJSONObject("data").getJSONArray("prices");
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Price price = (Price) gson.fromJson(jsonArray.getJSONObject(i).toString(), Price.class);
                            foods.add(price);
                        }
                        if (shimmerFrameLayout.isShimmerStarted()) {
                            CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {

                                }

                                @Override
                                public void onFinish() {
                                    shimmerFrameLayout.stopShimmer();
                                    shimmerFrameLayout.setVisibility(View.GONE);
                                }
                            }.start();

                        }
                        foodAdapter.notifyDataSetChanged();
                        foodAdapter.setLoaded();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void countAllFood() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API.COUNT_PRICE, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("code") == 0) {
                        JSONObject data = response.getJSONObject("data");
                        allFoods = data.getInt("count");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imMenu:
                initDialogMenu();
                break;
        }
    }

    @SuppressLint("NewApi")
    private void initDialogMenu() {
        dialog = new Dialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        dialog.setContentView(R.layout.dialog_menu);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        Button btnSearch = dialog.findViewById(R.id.btnSearch);
        Button btnCategory = dialog.findViewById(R.id.btnCategory);
        Button btnMap = dialog.findViewById(R.id.btnMap);
        Button btnRegister = dialog.findViewById(R.id.btnResgisterRestaurant);
        Button btnLogin = dialog.findViewById(R.id.btnLogin);
        Button btnRestaurant = dialog.findViewById(R.id.btnRestaurant);

        final String userStr = Preferences.getData(Key.USER, Main2Activity.this);
        if (!userStr.equals("")){
            btnLogin.setText(getString(R.string.logout));
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, SearchActivity.class));
                dialog.dismiss();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userStr.equals("")){
                    Preferences.saveData(Key.USER, "", Main2Activity.this);
                    dialog.dismiss();
                }else {
                    startActivity(new Intent(Main2Activity.this, LoginRegisterActivity.class));
                    dialog.dismiss();
                }
            }
        });

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, CategoryActivity.class));
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(Main2Activity.this, FoodDetailActivity.class);
        intent.putExtra(Key.FOOD, foods.get(position));
        startActivity(intent);
    }

    @Override
    public void onButtonClick(View view, int position) {

    }
}