package com.example.sharefood.util;

import android.content.Context;

import com.example.sharefood.R;

import dmax.dialog.SpotsDialog;

public class ProcessDialog {
    private SpotsDialog progressDialog;


    public ProcessDialog(Context context) {
        progressDialog = new SpotsDialog(context, R.style.Custom);
    }

    public void show(){
        progressDialog.show();
    }

    public void dismiss(){
        progressDialog.dismiss();
    }

    public boolean isShow(){
        return progressDialog.isShowing();
    }

}