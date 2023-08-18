package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class  MainActivity2 extends AppCompatActivity {

    TextView tvID;
    TextView tvName;
    LinearLayout layout;
    LinearLayout linear1,linear0;
    String nam;
    String CtcPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvID=(TextView) findViewById(R.id.tvID);
        tvName=(TextView) findViewById(R.id.tvName);
        linear0=(LinearLayout) findViewById(R.id.linear0);
        layout=(LinearLayout) findViewById(R.id.layout);
        linear1=(LinearLayout) findViewById(R.id.linear1);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CtcPage=tvID.getText().toString();

                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("CtcPage",CtcPage);
                startActivity(intent);

            }
        });

        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity2.this, MainActivity4.class);

                startActivity(i);

            }
        });

        linear0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity2.this, MainActivity5.class);

                startActivity(i);

            }
        });

        CtcPage=getIntent().getStringExtra("CtcPage");
        tvID.setText(CtcPage);

        nam=getIntent().getStringExtra("Name");
        tvName.setText(nam);

    }
}