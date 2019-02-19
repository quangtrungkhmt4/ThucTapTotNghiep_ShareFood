package com.example.admin.thuctaptotnghiep.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private static final String KEY_APP = "SHARE_FOOD";

    public static void saveData(String key, String value, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_APP, Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getData(String key, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_APP, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

}
