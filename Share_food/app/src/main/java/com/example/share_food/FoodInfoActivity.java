package com.example.share_food;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.share_food.adapters.CommentAdapter;
import com.example.share_food.config.API;
import com.example.share_food.config.KeyPref;
import com.example.share_food.model.Comment;
import com.example.share_food.model.Food;
import com.example.share_food.model.User;
import com.example.share_food.utils.Database;
import com.example.share_food.utils.SharedPref;
import com.example.share_food.views.CustomFontTextView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FoodInfoActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_LOGIN = 999;
    private Toolbar toolbar;
    private ShimmerFrameLayout shimmerFrameLayout;
    private ImageView imageViewLogo;
    private CustomFontTextView tvName, tvRecipe, tvDesc;
    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton btnComment, btnFavorite, btnCheckRestaurant;
    private Database database;
    private Food currentFood;
    private Dialog dialogComment;
    private User user;

    private List<Comment> comments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);

        findId();
        initViews();
        loadData();
    }

    private void findId() {
        toolbar = findViewById(R.id.toolbarInfo);
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayoutInfo);
        imageViewLogo = findViewById(R.id.imgInfoFood);
        tvName = findViewById(R.id.tvNameInfo);
        tvRecipe = findViewById(R.id.tvRecipeInfoFood);
        tvDesc = findViewById(R.id.tvDescInfoFood);
        floatingActionMenu = findViewById(R.id.floatingInfo);
        btnComment = findViewById(R.id.floatingComment);
        btnFavorite = findViewById(R.id.floatingFavorite);
        btnCheckRestaurant = findViewById(R.id.floatingCheckRestaurant);
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
            Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.infoFood);
        }
        database = new Database(this);
        btnFavorite.setOnClickListener(this);
        btnCheckRestaurant.setOnClickListener(this);
        btnComment.setOnClickListener(this);

    }


    private void loadData() {
        Intent intent = getIntent();
        Food food = (Food) intent.getSerializableExtra("food");
        currentFood = food;
        Glide.with(this).load(food.getImage()).into(imageViewLogo);
        tvName.setText(food.getName());
        tvRecipe.setText(food.getRecipe());
        tvDesc.setText(food.getDesc());
        CountDownTimer countDownTimer = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
            }
        }.start();

        if (!SharedPref.getData(KeyPref.USER_LOGIN,this).equals("")){
            user = new Gson().fromJson(SharedPref.getData(KeyPref.USER_LOGIN,this), User.class);
        }
    }

    private void loadComment(final String id, final CommentAdapter commentAdapter){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.API_GET_COMMENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jsonArray = (JsonArray) parser.parse(response);
                for (int i=0; i<jsonArray.size(); i++){
                    Comment comment = gson.fromJson(jsonArray.get(i), Comment.class);
                    comments.add(comment);
                }
                commentAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FoodInfoActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("food", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void initDialogComment() {
        dialogComment = new Dialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            dialogComment.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        else {
            dialogComment.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        dialogComment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogComment.setContentView(R.layout.dialog_comment);
        dialogComment.setCancelable(true);

        ListView lvComment = dialogComment.findViewById(R.id.lvComment);
        final EditText edtComment = dialogComment.findViewById(R.id.edtComment);
        ImageButton btnSendComment = dialogComment.findViewById(R.id.btnSendComment);
        final CommentAdapter commentAdapter = new CommentAdapter(this, R.layout.item_comment, comments);
        lvComment.setAdapter(commentAdapter);

        comments.clear();
        loadComment(String.valueOf(currentFood.getId()), commentAdapter);

        btnSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edtComment.getText().toString();
                if (text.isEmpty()){
                    Toast.makeText(FoodInfoActivity.this, "Bạn chưa nhập bình luận.", Toast.LENGTH_SHORT).show();
                    return;
                }
                pushComment(String.valueOf(user.getId()), String.valueOf(currentFood.getId()), text, commentAdapter);
                edtComment.setText("");
            }
        });
        dialogComment.show();
    }

    private void pushComment(final String idCustomer, final String idFood, final String text, final CommentAdapter commentAdapter){
        comments.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.API_PUSH_COMMENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true")){
                    Toast.makeText(FoodInfoActivity.this, "Bình luận thành công.", Toast.LENGTH_SHORT).show();
                    loadComment(idFood, commentAdapter);
                }else {
                    Toast.makeText(FoodInfoActivity.this, "Lỗi không xác định", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FoodInfoActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idCustomer", idCustomer);
                params.put("idFood", idFood);
                params.put("text", text);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatingFavorite:
                List<Food> foodList = database.getFood();
                boolean isExists = false;
                for (Food f : foodList){
                    if (f.getId() == currentFood.getId()){
                        isExists = true;
                        break;
                    }
                }
                if (isExists){
                    Toast.makeText(this, "Món ăn này đã có trong danh sách yêu thích của bạn.", Toast.LENGTH_SHORT).show();
                }else {
                    database.insert(currentFood);
                    Toast.makeText(this, "Đã thêm món ăn này vào danh sách yêu thích của bạn.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.floatingCheckRestaurant:
                Intent intent = new Intent(this, RestaurantActivity.class);
                intent.putExtra("idFood", currentFood.getId());
                startActivity(intent);
                break;
            case R.id.floatingComment:
                if (user == null){
                    Toast.makeText(this, "Bạn cần đăng nhập để thực hiện bình luận.", Toast.LENGTH_SHORT).show();
                    startActivityForResult(new Intent(FoodInfoActivity.this, LoginActivity.class), REQUEST_LOGIN);
                }else {
                    initDialogComment();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FoodInfoActivity.REQUEST_LOGIN){
            if (resultCode == 1){
                if (!SharedPref.getData(KeyPref.USER_LOGIN,this).equals("")){
                    user = new Gson().fromJson(SharedPref.getData(KeyPref.USER_LOGIN,this), User.class);
                }
            }
        }
    }
}
