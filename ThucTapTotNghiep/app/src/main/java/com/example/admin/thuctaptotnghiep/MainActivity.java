package com.example.admin.thuctaptotnghiep;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.thuctaptotnghiep.adapters.CategoryAdapter;
import com.example.admin.thuctaptotnghiep.adapters.FoodAdapter;
import com.example.admin.thuctaptotnghiep.config.KeyPref;
import com.example.admin.thuctaptotnghiep.model.Food;
import com.example.admin.thuctaptotnghiep.model.ItemService;
import com.example.admin.thuctaptotnghiep.utils.SharedPref;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static com.example.admin.thuctaptotnghiep.config.API.API_GET_ALL_FOOD;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public static final int REQUEST_LOGIN = 100;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private ListView lvCategory;
    private CategoryAdapter categoryAdapter;
    private ListView lvFood;
    private FoodAdapter foodAdapter;
    private ShimmerFrameLayout shimmerFrameLayout;
    private ImageView imgLogin;
    private ImageView imLogout;

    private List<ItemService> itemServices = new ArrayList<>();
    private List<Food> foods = new ArrayList<>();

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
            List<Address> addresses = geocoder.getFromLocation(location.get("lattitude"), location.get("longitude"), 1);
            String cityName = addresses.get(0).getAdminArea();
            Toast.makeText(this, cityName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getAllFood();

    }


    private void findId() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbarMain);
        lvCategory = findViewById(R.id.lvCategory);
        lvFood = findViewById(R.id.lvFood);
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout);
        imgLogin = findViewById(R.id.imLogin);
        imLogout = findViewById(R.id.imLogout);
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

        categoryAdapter = new CategoryAdapter(this,R.layout.item_listview_category, itemServices);
        lvCategory.setAdapter(categoryAdapter);
        foodAdapter = new FoodAdapter(this, R.layout.item_listview_food, foods);
        lvFood.setAdapter(foodAdapter);
        lvFood.setOnItemClickListener(this);
        imgLogin.setOnClickListener(this);
        imLogout.setOnClickListener(this);

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
        ItemService itemService3 = new ItemService("restaurant", R.drawable.ic_restaurant, "Danh sách nhà hàng");
        ItemService itemService4 = new ItemService("province", R.drawable.ic_provnice, "Tỉnh/Thành phố");
        ItemService itemService5 = new ItemService("about", R.drawable.ic_about, "Thông tin");
        itemServices.add(itemService);
        itemServices.add(itemService1);
        itemServices.add(itemService2);
        itemServices.add(itemService3);
        itemServices.add(itemService4);
        itemServices.add(itemService5);
        categoryAdapter.notifyDataSetChanged();
    }

    private void checkLogin() {
        String user = SharedPref.getData(KeyPref.USER_LOGIN, this);
        if (user.equalsIgnoreCase("")){
            imLogout.setVisibility(View.GONE);
        }else {
            imgLogin.setVisibility(View.GONE);
        }
    }

    private Collection<Food> getAllFood(){
        final Collection<Food> foodCollection = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(API_GET_ALL_FOOD, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i< response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        int id = object.getInt("idFood");
                        String name = object.getString("name");
                        String image = object.getString("image");
                        String recipe = object.getString("recipe");
                        int idCate = object.getInt("idFoodCategory");
                        String desc = object.getString("description");
                        Food food = new Food(id,name,image,recipe,idCate,desc);
                        foodCollection.add(food);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
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

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN){
            Toast.makeText(this, "hihi", Toast.LENGTH_SHORT).show();
        }
    }
}
