package com.example.share_food;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.share_food.adapters.FoodAdapter;
import com.example.share_food.adapters.ItemDrawerLayout;
import com.example.share_food.adapters.ProvinceAdapter;
import com.example.share_food.config.API;
import com.example.share_food.config.KeyPref;
import com.example.share_food.model.Food;
import com.example.share_food.model.ItemService;
import com.example.share_food.model.Province;
import com.example.share_food.utils.SharedPref;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public static final int REQUEST_LOGIN = 100;
    public static final int REQUEST_CATE = 101;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private ListView lvCategory;
    private ItemDrawerLayout categoryAdapter;
    private ListView lvFood;
    private FoodAdapter foodAdapter;
    private ShimmerFrameLayout shimmerFrameLayout;
    private ImageView imgLogin;
    private ImageView imLogout;
    private FloatingActionButton btnSearch;
    private Dialog dialogProvince;
    private Dialog dialogAbout;
    private List<Province> provinces = new ArrayList<>();

    private List<ItemService> itemServices = new ArrayList<>();
    private List<Food> foods = new ArrayList<>();
    private Double lat;
    private Double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findId();
        initViews();
        getData();
        checkLogin();

        Intent intent = getIntent();
        Map<String, Double> location = (Map<String, Double>) intent.getSerializableExtra("location");
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            lat = location.get("lattitude");
            lon = location.get("longitude");
            List<Address> addresses = geocoder.getFromLocation(location.get("lattitude"), location.get("longitude"), 1);
//            String cityName = addresses.get(0).getAdminArea();
            String cityName = "Hà Nội";
            getAllFoodFollowProvince(cityName);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    private void findId() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbarMain);
        lvCategory = findViewById(R.id.lvCategory);
        lvFood = findViewById(R.id.lvFood);
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout);
        imgLogin = findViewById(R.id.imLogin);
        imLogout = findViewById(R.id.imLogout);
        btnSearch = findViewById(R.id.floatingMain);
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        }

        toggle = new ActionBarDrawerToggle(this,drawerLayout,0,0){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        categoryAdapter = new ItemDrawerLayout(this,R.layout.item_listview_category, itemServices);
        lvCategory.setAdapter(categoryAdapter);
        foodAdapter = new FoodAdapter(this, R.layout.item_listview_food, foods);
        lvFood.setAdapter(foodAdapter);
        lvFood.setOnItemClickListener(this);
        lvCategory.setOnItemClickListener(this);
        imgLogin.setOnClickListener(this);
        imLogout.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        shimmerFrameLayout.startShimmer();


    }

    private void getData() {
//        String listCategory = SharedPref.getData("LIST_CATEGORIES", this);
//        Gson gson = new Gson();
//        JsonParser jsonParser = new JsonParser();
//        JsonArray jsonArray = jsonParser.parse(listCategory).getAsJsonArray();
//        for (int i=0; i<jsonArray.size(); i++){
//            JsonObject object = (JsonObject) jsonArray.get(i);
//            Category category = gson.fromJson(object,Category.class);
//            itemServices.add(category);
//        }
        ItemService itemService = new ItemService("category", R.drawable.ic_category, "Danh sách loại món ăn");
        ItemService itemService1 = new ItemService("map", R.drawable.ic_map, "Bản đồ");
        ItemService itemService2 = new ItemService("favorite", R.drawable.ic_favorite, "Danh sách yêu thích");
//        ItemService itemService3 = new ItemService("restaurant", R.drawable.ic_restaurant, "Danh sách nhà hàng");
        ItemService itemService4 = new ItemService("province", R.drawable.ic_provnice, "Tỉnh/Thành phố");
        ItemService itemService5 = new ItemService("about", R.drawable.ic_about, "Thông tin");
        itemServices.add(itemService);
        itemServices.add(itemService1);
        itemServices.add(itemService2);
//        itemServices.add(itemService3);
        itemServices.add(itemService4);
        itemServices.add(itemService5);
        categoryAdapter.notifyDataSetChanged();
    }

    private void checkLogin() {
        String user = SharedPref.getData(KeyPref.USER_LOGIN, this);
        if (user.equalsIgnoreCase("")){
            imLogout.setVisibility(View.GONE);
            imgLogin.setVisibility(View.VISIBLE);
        }else {
            imgLogin.setVisibility(View.GONE);
            imLogout.setVisibility(View.VISIBLE);
        }
    }

    private Collection<Food> getAllFoodFollowProvince(final String province) throws JSONException {
        foods.clear();
        final Collection<Food> foodCollection = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.API_GET_ALL_FOOD_FOLLOW_PROVINCE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jsonArray = parser.parse(response).getAsJsonArray();
                for (int i=0; i<jsonArray.size(); i++){
                    JsonObject object = jsonArray.get(i).getAsJsonObject();
                    int id = object.get("id").getAsInt();
                    String name = object.get("name").getAsString();
                    String image = object.get("image").getAsString();
                    String recipe = object.get("recipe").getAsString();
                    int idCate = object.get("category").getAsInt();
                    String desc = object.get("desc").getAsString();
                    Food food = new Food(id,name,image,recipe,idCate,desc);
                    foodCollection.add(food);
                }
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                foods.addAll(foodCollection);
                foodAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("-------", error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("province", province);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        return foodCollection;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lvFood:
                Food currentFood = foods.get(position);
                Intent intent = new Intent(this, FoodInfoActivity.class);
                intent.putExtra("food", currentFood);
                startActivity(intent);
                break;
            case R.id.lvCategory:
                if (position == 0){
                    Intent intent1 = new Intent(this, CategoryActivity.class);
                    startActivityForResult(intent1, REQUEST_CATE);
                }else if (position == 1){
                    String uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat, lon);
                    Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent2);
                }else if(position == 2){
                    Intent intent3 = new Intent(this, FavoriteActivity.class);
                    startActivityForResult(intent3, REQUEST_CATE);
                }else if (position == 3){
                    drawerLayout.closeDrawers();
                    initDialogProvince();
                }else if (position == 4){
                    drawerLayout.closeDrawers();
                    initDialogAbout();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imLogin:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent, REQUEST_LOGIN);
                break;
            case R.id.imLogout:
                SharedPref.saveData(KeyPref.USER_LOGIN, "", this);
                recreate();
                break;
            case R.id.floatingMain:
                Intent intentSearch = new Intent(this, SearchActivity.class);
                startActivity(intentSearch);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN){
            if (resultCode == 1){
                imgLogin.setVisibility(View.GONE);
                imLogout.setVisibility(View.VISIBLE);
            }
        }else if (requestCode == REQUEST_CATE){
            drawerLayout.closeDrawers();
            if (resultCode == 2){
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                assert data != null;
                JsonArray jsonArray = parser.parse(data.getStringExtra("foods")).getAsJsonArray();
                foods.clear();
                for (int i=0; i<jsonArray.size(); i++){
                    Food food = gson.fromJson(jsonArray.get(i), Food.class);
                    foods.add(food);
                }
                foodAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLogin();
    }

    private void initDialogProvince() {
        dialogProvince = new Dialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            dialogProvince.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        else {
            dialogProvince.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        dialogProvince.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogProvince.setContentView(R.layout.dialog_province);
        dialogProvince.setCancelable(false);

        ListView lvProvince = dialogProvince.findViewById(R.id.lvProvince);
        ImageView imBack = dialogProvince.findViewById(R.id.imgDimissDialogProvince);
        ProvinceAdapter provinceAdapter = new ProvinceAdapter(this, R.layout.item_province, provinces);
        lvProvince.setAdapter(provinceAdapter);

        getProvinces(provinceAdapter);

        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogProvince.dismiss();
            }
        });

        lvProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    getAllFoodFollowProvince(provinces.get(position).getName());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialogProvince.dismiss();
            }
        });

        dialogProvince.show();
    }

    private void initDialogAbout() {
        dialogAbout = new Dialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            dialogAbout.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        else {
            dialogAbout.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        dialogAbout.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogAbout.setContentView(R.layout.dialog_about);
        dialogAbout.setCancelable(true);

        dialogAbout.show();
    }

    private void getProvinces(final ProvinceAdapter provinceAdapter){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(API.API_GET_PROVINCE, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        Province province = new Province(object.getInt("id"), object.getString("name"), object.getString("info"), object.getString("latLong"));
                        provinces.add(province);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                provinceAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
