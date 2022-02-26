package com.vektorel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
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
import com.vektorel.utility.StaticValues;

public class RegisterActivity extends AppCompatActivity {

    EditText txtusername,password,repassword,answer;
    Button btnregister;
    private static final String DEBUG_TAG = "NetworkStatusExample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
        }
        Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);
        Log.d(DEBUG_TAG, "Mobile connected: " + isMobileConn);
        /**
         * Hangi sayfanını kullanılacağını bilmelisiniz ve ayrıca
         * Kullanılacak sayfa yüklenmiş olmalı
         */
        txtusername = findViewById(R.id.txtusername);
        password = findViewById(R.id.txtpassword);
        repassword = findViewById(R.id.txtrepassowrd);
        answer = findViewById(R.id.txtanswer);
        btnregister = findViewById(R.id.btnuyeol);
        /**
         * Listener -> Bir bileşeni dinlemek üzere oluşturulur.
         * ve o bileşen içinde bir olay olursa işlem yapar.
         */
        btnregister.setOnClickListener(v -> register());
    }

    private void register() {
        if(txtusername.getText().toString().equals("") || password.getText().toString().equals(""))
            Toast.makeText(this, "Kullanıcı adı ya da şifre boş geçilemez", Toast.LENGTH_SHORT).show();
        else if(!password.getText().toString().equals(repassword.getText().toString()))
            Toast.makeText(this, "Şifreler Uyuşmuyor.", Toast.LENGTH_SHORT).show();
        else{
           registerUser();

        }

    }

    private void registerUser(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = StaticValues.AWSSERVER+ "/restuser/saveuser?" +
                "answer=" + answer.getText().toString()+
                "&password=" + password.getText().toString()+
                "&question=1" +
                "&username="+ txtusername.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /**
                         * Yapılan istek başarılı ise burada işlem yaparsın
                         */
                    sendState(true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /**
                 * Hata almış ise burada işlem yaparsın.
                 */
                Log.e("TAG", "onErrorResponse: "+error.toString());
                sendState(false);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void sendState(boolean isState){
        if (isState){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Üyelik başarı ile gerçekleşti.", Toast.LENGTH_LONG).show();
        }

        else

            Toast.makeText(this, "Beklenemeyen bir hata oldu.", Toast.LENGTH_LONG).show();
    }

}