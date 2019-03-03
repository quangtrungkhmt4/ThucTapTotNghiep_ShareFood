package com.example.share_food;

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
import com.example.share_food.config.API;
import com.example.share_food.views.CustomEditText;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomEditText edtUserName, edtPass, edtRePass, edtName;
    private Button btnBack, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findID();
        initViews();
    }

    private void findID() {
        edtUserName = findViewById(R.id.edtUserNameRegister);
        edtPass = findViewById(R.id.edtPasswordRegister);
        edtRePass = findViewById(R.id.edtConfirmPasswordRegister);
        edtName = findViewById(R.id.edtNameRegister);
        btnBack = findViewById(R.id.btnBackRegister);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void initViews() {
        btnBack.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBackRegister:
                finish();
                break;
            case R.id.btnRegister:
                final String user = edtUserName.getText().toString();
                final String pass = edtPass.getText().toString();
                final String rePass = edtRePass.getText().toString();
                final String name = edtName.getText().toString();

                if (user.isEmpty() || pass.isEmpty() || rePass.isEmpty() || name.isEmpty()){
                    Toast.makeText(this, " Bạn cần nhập đủ thông tin.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pass.equalsIgnoreCase(rePass)){
                    Toast.makeText(this, "Hai mật khẩu của bạn không giống nhau.", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringRequest stringRequest = new StringRequest(Request.Method.POST, API.API_REGISTER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("exists")){
                            Toast.makeText(RegisterActivity.this, "Tên tài khoản đã tồn tại.", Toast.LENGTH_SHORT).show();
                        }else if (response.equalsIgnoreCase("false")){
                            Toast.makeText(RegisterActivity.this, "Đăng ký thất bại.", Toast.LENGTH_SHORT).show();
                        }else if (response.equalsIgnoreCase("true")){
                            Toast.makeText(RegisterActivity.this, "Thành công.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", user);
                        params.put("password", pass);
                        params.put("name", name);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

                break;
        }
    }
}
