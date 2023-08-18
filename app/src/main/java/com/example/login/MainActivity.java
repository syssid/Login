package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.QwertyKeyListener;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Authenticator;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
// CP0001 111 1000
    EditText etUser;
    EditText etPassword;
    EditText etSecurity;
    Button btnClick;
    Pref    pref;
    String aeme;
    String masterID;
    String pass;
    String securityCode;
    String  CtcPage;
    String name;
    String passwordconverted;
    String AEMEmployeeID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        etUser=(EditText) findViewById(R.id.etUser);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etSecurity=(EditText)findViewById(R.id.etSecurity);

        btnClick=(Button) findViewById(R.id.btnClick);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masterID=etUser.getText().toString();
                pass=etPassword.getText().toString();
                securityCode=etSecurity.getText().toString();
                byte[] data = new byte[0];
                data = pass.getBytes(StandardCharsets.UTF_8);
                String base64 = Base64.encodeToString(data, Base64.DEFAULT);
                passwordconverted=base64.replaceAll("\\n","");

              Authenticate();
            }
        });
    }
    private void   Authenticate() {
        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();
        String surl = "https://cloud.geniusconsultant.com/GHRMSApi/api/AuthenticateWithEncryption?MasterID="+masterID+"&Password="+passwordconverted+"&IMEI=82bff7e3bb117cc5&Version=v1&SecurityCode="+securityCode+"&DeviceID=1234&DeviceType=A";
        Log.d("attendance", surl);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, surl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseLogin", response);
                        pd.dismiss();

                        try {
                            JSONObject job1 = new JSONObject(response);
                            Log.e("response12", "@@@@@@" + job1);
                            String responseText = job1.optString("responseText");
                            boolean responseStatus = job1.optBoolean("responseStatus");
                            if (responseStatus) {



                                JSONArray responseData = job1.optJSONArray("responseData");
                                for (int i = 0; i < responseData.length(); i++) {
                                    JSONObject obj = responseData.optJSONObject(i);
                                    name = obj.optString("Name");
                                    CtcPage = obj.optString("CtcPage");
                                }

                                Intent intent = new Intent (MainActivity.this,MainActivity2.class);
                                intent.putExtra("Name",name);

                                intent.putExtra("CtcPage",CtcPage);
                                startActivity(intent);



                            } else {
                                //Toast.makeText(getApplicationContext(), responseText, Toast.LENGTH_LONG).show();
                            }
                            // boolean _status = job1.getBoolean("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(MainActivity.this, "volly 2" + error.toString(), Toast.LENGTH_LONG).show();

                Log.e("ert", error.toString());
            }
        }) {

        };


        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

}