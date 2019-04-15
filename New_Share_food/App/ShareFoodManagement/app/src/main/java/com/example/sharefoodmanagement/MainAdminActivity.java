package com.example.sharefoodmanagement;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.example.sharefoodmanagement.adapter.PageAdapter;
import com.example.sharefoodmanagement.constant.Key;
import com.example.sharefoodmanagement.model.User;
import com.example.sharefoodmanagement.util.Preferences;
import com.example.sharefoodmanagement.util.ProcessDialog;
import com.example.sharefoodmanagement.util.VolleySingleton;
import com.google.gson.Gson;

public class MainAdminActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private PageAdapter pagerAdapter;
    private RequestQueue requestQueue;
    private ProcessDialog processDialog;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        findId();
        initViews();
    }

    private void findId() {
        toolbar = findViewById(R.id.toolbarManager);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.sliding_tabs);
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        requestQueue = VolleySingleton.getInstance(this).getmRequestQueue();
        processDialog = new ProcessDialog(this);
        currentUser = new Gson().fromJson(Preferences.getData(Key.USER, this), User.class);
        pagerAdapter = new PageAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
