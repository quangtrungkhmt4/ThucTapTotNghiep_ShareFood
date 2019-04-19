package com.example.sharefoodmanagement.fragment;

import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.sharefoodmanagement.MainManagerActivity;
import com.example.sharefoodmanagement.R;
import com.example.sharefoodmanagement.constant.API;
import com.example.sharefoodmanagement.constant.Key;
import com.example.sharefoodmanagement.model.Manager;
import com.example.sharefoodmanagement.model.Restaurant;
import com.example.sharefoodmanagement.model.User;
import com.example.sharefoodmanagement.util.Preferences;
import com.example.sharefoodmanagement.util.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class RestaurantInfoFragment extends Fragment {

    private EditText edtName, edtOpen, edtClose, edtAdress, edtPhone, edtWeb;
    private Button btnUpdate;
    private CircleImageView logo;
    private MainManagerActivity mainManagerActivity;
    private RequestQueue requestQueue;
    private User currentUser;
    private Manager currentManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_info, container, false);
        findId(view);
        initViews();
        // Inflate the layout for this fragment
        return view;
    }

    private void findId(View view) {
        logo = view.findViewById(R.id.imgLogoRes);
        edtName = view.findViewById(R.id.edtNameRes);
        edtOpen = view.findViewById(R.id.edtOpenTimeRes);
        edtClose = view.findViewById(R.id.edtCloseTimeRes);
        edtAdress = view.findViewById(R.id.edtAddressRes);
        edtPhone = view.findViewById(R.id.edtPhoneRes);
        edtWeb = view.findViewById(R.id.edtWebsiteRes);
        btnUpdate = view.findViewById(R.id.btnUpdateRes);
    }

    private void initViews() {
        mainManagerActivity = (MainManagerActivity) getActivity();
        requestQueue = VolleySingleton.getInstance(getContext()).getmRequestQueue();

        currentUser = new Gson().fromJson(Preferences.getData(Key.USER, mainManagerActivity), User.class);

        getManager(currentUser.getId());

        edtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(mainManagerActivity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String h = String.valueOf(selectedHour);
                        String m = String.valueOf(selectedMinute);
                        if (selectedHour < 10){
                            h = "0" + h;
                        }
                        if (selectedMinute < 10){
                            m = "0" + m;
                        }
                        edtClose.setText( h + ":" + m);
                        currentManager.getRestaurant().setTime_close(h + ":" + m);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Chọn thời gian");
                mTimePicker.show();
            }
        });

        edtOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(mainManagerActivity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String h = String.valueOf(selectedHour);
                        String m = String.valueOf(selectedMinute);
                        if (selectedHour < 10){
                            h = "0" + h;
                        }
                        if (selectedMinute < 10){
                            m = "0" + m;
                        }
                        edtOpen.setText( h + ":" + m);
                        currentManager.getRestaurant().setTime_open(h + ":" + m);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Chọn thời gian");
                mTimePicker.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String open = edtOpen.getText().toString();
                String close = edtClose.getText().toString();
                String address = edtAdress.getText().toString();
                String phone = edtPhone.getText().toString();
                String web = edtWeb.getText().toString();

                if (name.isEmpty() || open.isEmpty() || close.isEmpty() || address.isEmpty() || phone.isEmpty() || web.isEmpty()){
                    Toast.makeText(mainManagerActivity, getString(R.string.into_info), Toast.LENGTH_SHORT).show();
                    return;
                }

                Restaurant res = currentManager.getRestaurant();
                res.setName(name);
                res.setTime_open(open);
                res.setTime_close(close);
                res.setAddress(address);
                res.setPhone(phone);
                res.setWebsite(web);

                putRestaurant(res);

            }
        });

    }

    private void getManager(int idUser){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API.GET_MANAGER+"?id_user=" + idUser, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    if (response.getInt("code") == 0) {
                        JSONObject dataStr = response.getJSONObject("data").getJSONObject("manager");

                        currentManager = new Gson().fromJson(dataStr.toString(), Manager.class);
                        Preferences.saveData(Key.MANAGER, new Gson().toJson(currentManager), mainManagerActivity);

                        loadData();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainManagerActivity, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void loadData() {
        Glide.with(mainManagerActivity).load(currentManager.getRestaurant().getLogo()).into(logo);
        edtName.setText(currentManager.getRestaurant().getName());
        edtOpen.setText(currentManager.getRestaurant().getTime_open());
        edtClose.setText(currentManager.getRestaurant().getTime_close());
        edtAdress.setText(currentManager.getRestaurant().getAddress());
        edtPhone.setText(currentManager.getRestaurant().getPhone());
        edtWeb.setText(currentManager.getRestaurant().getWebsite());
    }

    private void putRestaurant(final Restaurant restaurant) {

        JSONObject param = null;
        try {
            param = new JSONObject(new Gson().toJson(restaurant));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, API.POST_RESTAURANT, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("code") == 0) {
                        Toast.makeText(mainManagerActivity, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainManagerActivity, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


}
