package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Year;
import java.util.ArrayList;


public class MainActivity4 extends AppCompatActivity {

    RecyclerView rvReport;
    ArrayList<ModelActivity> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        rvReport = (RecyclerView) findViewById(R.id.rvReport);
        rvReport.setLayoutManager(new LinearLayoutManager(this));

        getHoliday();
    }
    private void getHoliday() {
        ProgressDialog pd = new ProgressDialog(MainActivity4.this);
        pd.setMessage("Loading..");
        pd.setCancelable(false);
        pd.show();
        String surl = "https://cloud.geniusconsultant.com/GeniusESS/API/ManageEmployee/GetHoliday?CompanyID=1090000001&BranchID=1100000001&Year=2022";
        Log.d("holidays", surl);

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
                                    String HolidayName = obj.optString("HolidayName");
                                    String HolidayDayName = obj.optString("HolidayDayName");
                                    String HolidayDate = obj.optString("HolidayDate");

                                    Log.d("HolidayName", HolidayName);
                                    Log.d("HolidayDayName", HolidayDayName);
                                    Log.d("HolidayDate", HolidayDate);
                                    ModelActivity myModel = new ModelActivity(HolidayName, HolidayDayName, HolidayDate);
                                    itemList.add(myModel);

                                }
                                ReportAdapter reportAdapter = new ReportAdapter(itemList);
                                rvReport.setAdapter(reportAdapter);

                            } else {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity4.this, "Volly Error", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(MainActivity4.this, "volly 2" + error.toString(), Toast.LENGTH_LONG).show();

                Log.e("ert", error.toString());
            }
        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity4.this);
        requestQueue.add(stringRequest);
    }

}