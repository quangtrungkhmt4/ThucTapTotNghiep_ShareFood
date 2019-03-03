package com.example.share_food.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.share_food.model.Food;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Database {
    private static final String PATH = Environment.getDataDirectory().getPath()
            + "/data/com.example.share_food/databases/";

    private static final String DB_NAME = "favorite.sqlite";
    private static final String TABLE = "food";


    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String IMAGE = "image";


    private static final String DESC = "desc";
    private static final String RECIPE = "recipe";
    private static final String CATEGORY = "category";


    private Context context;
    private SQLiteDatabase database;

    public Database(Context context) {
        this.context = context;
        copyFileToDevice();
    }

    private void copyFileToDevice() {
        File file = new File(PATH + DB_NAME);
        if (!file.exists()) {
            File parent = file.getParentFile();
            parent.mkdirs();
            try {
                InputStream inputStream = context.getAssets().open(DB_NAME);
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int count = inputStream.read(b);
                while (count != -1) {
                    outputStream.write(b, 0, count);
                    count = inputStream.read(b);
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openDatabase() {
        database = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    }

    private void closeDatabase() {
        database.close();
    }

    public ArrayList<Food> getFood() {
        ArrayList<Food> arr = new ArrayList<>();
        openDatabase();
        @SuppressLint("Recycle") Cursor cursor = database.query(TABLE, null, null, null, null, null, null);
        cursor.moveToFirst();

        int indexID = cursor.getColumnIndex(ID);
        int indexName = cursor.getColumnIndex(NAME);
        int indexImage = cursor.getColumnIndex(IMAGE);
        int indexDesc = cursor.getColumnIndex(DESC);
        int indexRecipe = cursor.getColumnIndex(RECIPE);
        int indexCategory = cursor.getColumnIndex(CATEGORY);

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(indexID);
            String name = cursor.getString(indexName);
            String image = cursor.getString(indexImage);
            String desc = cursor.getString(indexDesc);
            String recipe = cursor.getString(indexRecipe);
            int category = cursor.getInt(indexCategory);
            Food food = new Food(id, name, image, recipe, category, desc);
            arr.add(food);
            cursor.moveToNext();
        }
        closeDatabase();
        return arr;
    }

    public boolean delete(int id) {
        openDatabase();
        boolean isDone = database.delete(TABLE, "id" + "=" + id, null) > 0;
        closeDatabase();
        return isDone;
    }

    public void insert(Food food) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put("id", food.getId());
        values.put("name", food.getName());
        values.put("image", food.getImage());
        values.put("desc", food.getDesc());
        values.put("recipe", food.getRecipe());
        values.put("category", food.getIdCate());
        database.insert(TABLE, null, values);
        closeDatabase();
    }

}