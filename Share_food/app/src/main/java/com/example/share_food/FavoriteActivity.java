package com.example.share_food;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.share_food.adapters.FoodAdapter;
import com.example.share_food.model.Food;
import com.example.share_food.utils.Database;

import java.util.List;
import java.util.Objects;

public class FavoriteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private Toolbar toolbar;
    private ListView lvFood;
    private Database database;
    private FoodAdapter foodAdapter;
    private List<Food> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        findID();
        initViews();
    }

    private void findID() {
        toolbar = findViewById(R.id.toolbarFavorite);
        lvFood = findViewById(R.id.lvFoodFavorite);
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
        database = new Database(this);
        foods = database.getFood();
        foodAdapter = new FoodAdapter(this, R.layout.item_listview_food, foods);
        lvFood.setAdapter(foodAdapter);
        lvFood.setOnItemClickListener(this);
        lvFood.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Food currentFood = foods.get(position);
        Intent intent = new Intent(this, FoodInfoActivity.class);
        intent.putExtra("food", currentFood);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn muốn xóa món ăn này khỏi danh sách yêu thích?")
                .setCancelable(false)
                .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        database.delete(foods.get(position).getId());
                        foods.clear();
                        foods.addAll(database.getFood());
                        foodAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
        return true;
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
