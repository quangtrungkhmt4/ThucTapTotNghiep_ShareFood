package com.example.admin.thuctaptotnghiep;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.admin.thuctaptotnghiep.model.Food;
import com.example.admin.thuctaptotnghiep.views.CustomFontTextView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.Objects;

public class FoodInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ShimmerFrameLayout shimmerFrameLayout;
    private ImageView imageViewLogo;
    private CustomFontTextView tvName, tvRecipe, tvDesc;
    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton btnComment, btnFavorite, btnCheckRestaurant;

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

    }


    private void loadData() {
        Intent intent = getIntent();
        Food food = (Food) intent.getSerializableExtra("food");
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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
