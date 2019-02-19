package com.example.admin.thuctaptotnghiep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.thuctaptotnghiep.config.API;
import com.example.admin.thuctaptotnghiep.model.User;
import com.example.admin.thuctaptotnghiep.views.CustomEditText;
import com.example.admin.thuctaptotnghiep.views.CustomFontTextView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomEditText edtUser, edtPass;
    private CustomFontTextView tvRegister;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findId();
        initViews();
    }

    private void findId() {
        edtUser = findViewById(R.id.edtUserNameLogin);
        edtPass = findViewById(R.id.edtPasswordLogin);
        tvRegister = findViewById(R.id.tvRegisterUser);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void initViews() {
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();

                if (user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(this, "Bạn phải nhập đủ Tên tài khoản và Mật khẩu!", Toast.LENGTH_SHORT).show();
                    return;
                }

                requestLogin(user, pass);

                break;
            case R.id.tvRegisterUser:

                break;
        }
    }

    private void requestLogin(final String user, final String pass){

//        JSONArray array = new JSONArray();
//        JSONObject params = new JSONObject();
//        try {
//            params.put("username", user);
//            params.put("password", pass);
//            array.put(params);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


//        JSONObject postparams = new JSONObject();
//        try {
//            postparams.put("username", user);
//            postparams.put("password", pass);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API.API_LOGIN, postparams, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                Log.e("---------", error.toString());
//            }
//        });

//        JSONArray params = new JSONArray();
//        JSONObject object = new JSONObject();
//        try {
//            object.put("username", user);
//            object.put("password", pass);
//            params.put(object);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, API.API_LOGIN, params, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                if (response.length() == 0){
//                    Toast.makeText(LoginActivity.this, "Tên tài khoản hoặc Mật khẩu không đúng.", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                Log.e("---------", error.toString());
//            }
//        });

        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.API_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jsonArray = (JsonArray) parser.parse(response);
                User u = gson.fromJson(jsonArray.get(0).toString(), User.class);
                Toast.makeText(LoginActivity.this, jsonArray.size()+"", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", user);
                params.put("password", pass);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
