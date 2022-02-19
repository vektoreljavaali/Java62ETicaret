package com.vektorel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {
    EditText txtusername,password;
    Button btngiris;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtusername= findViewById(R.id.txtloginusername);
        password = findViewById(R.id.txtloginpassword);
        btngiris = findViewById(R.id.btngiris);
        btngiris.setOnClickListener(v -> GirisYap());
    }

    private void GirisYap() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://ec2-18-118-103-244.us-east-2.compute.amazonaws.com/restuser/isuser?" +
                "password=" +password.getText().toString()+
                "&username="+txtusername.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /**
                         * Yapılan istek başarılı ise burada işlem yaparsın
                         */
                        goHome(true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /**
                 * Hata almış ise burada işlem yaparsın.
                 */
                Log.e("TAG", "onErrorResponse: "+error.toString());
                goHome(false);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void goHome(boolean isLogin){
        if (isLogin){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else
            Toast.makeText(this, "Kullanıı Adı ya da Şifr Hatalı", Toast.LENGTH_SHORT).show();
    }
}