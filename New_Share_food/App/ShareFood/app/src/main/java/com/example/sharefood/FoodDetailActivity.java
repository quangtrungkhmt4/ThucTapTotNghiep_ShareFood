package com.example.sharefood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sharefood.constant.Key;
import com.example.sharefood.model.Price;
import com.example.sharefood.view.CustomItalyTextView;

public class FoodDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imBack, imLogoFood;
    private CustomItalyTextView tvFoodName, tvResName, tvTime, tvAddress, tvWebsite, tvFoodInfo, tvRecipe, tvPrice;
    private Button btnCall;
    private Price currentPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        findId();
        initViews();
        loadData();
    }

    private void findId() {
        toolbar = findViewById(R.id.toolbarFoodDetail);
        imBack = findViewById(R.id.imBack);
        imLogoFood = findViewById(R.id.imgLogoFoodDetail);
        tvFoodName = findViewById(R.id.tvNameFoodDetail);
        tvResName = findViewById(R.id.tvNameResFoodDetail);
        tvTime = findViewById(R.id.tvTimeOpenFoodDetail);
        tvAddress = findViewById(R.id.tvAddressFoodDetail);
        tvWebsite = findViewById(R.id.tvWebsiteFoodDetail);
        tvFoodInfo = findViewById(R.id.tvFoodInfoFoodDetail);
        tvRecipe = findViewById(R.id.tvFoodRecipeFoodDetail);
        btnCall = findViewById(R.id.btnCallFoodDetail);
        tvPrice = findViewById(R.id.tvPriceFoodDetail);
    }

    private void initViews() {

    }

    private void loadData() {
        Intent intent = getIntent();
        currentPrice = (Price) intent.getSerializableExtra(Key.FOOD);

        Glide.with(this).load(currentPrice.getFood().getImage()).into(imLogoFood);
        tvFoodName.setText(currentPrice.getFood().getName().trim());
        tvResName.setText(currentPrice.getRestaurant().getName().trim());
        tvTime.setText(currentPrice.getRestaurant().getTime_open().trim() + " - " + currentPrice.getRestaurant().getTime_close().trim());
        tvAddress.setText(currentPrice.getRestaurant().getAddress().trim());
        tvWebsite.setText(currentPrice.getRestaurant().getWebsite().trim());
        tvFoodInfo.setText(currentPrice.getFood().getDescription().trim());
        tvRecipe.setText(currentPrice.getFood().getRecipe().trim());
        tvPrice.setText(currentPrice.getPrice() + " VND");

    }
}
