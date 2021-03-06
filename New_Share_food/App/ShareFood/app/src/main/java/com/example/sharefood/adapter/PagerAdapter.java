package com.example.sharefood.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sharefood.fragment.LoginFragment;
import com.example.sharefood.fragment.RegisterFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new LoginFragment();
        } else if (position == 1){
            return new RegisterFragment();
        }
        return null;
    }
    @Override
    public int getCount() {
        return 2;
    }

//    // This determines the title for each tab
//    @Override
//    public CharSequence getPageTitle(int position) {
//        // Generate title based on item position
//        switch (position) {
//            case 0:
//                return mContext.getString(R.string.restaurant_manager);
//            case 1:
//                return mContext.getString(R.string.user_manager);
//            default:
//                return null;
//        }
//    }

}