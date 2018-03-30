package com.ksapps.shelfshare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class NgoActivity extends AppCompatActivity {

    TextView textViewngo;
    Button ngo1;
    Button ngo2;
    Button ngo3;
    Button reqButton;
    EditText tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo);
        tvInfo = (EditText) findViewById(R.id.tvinfo);
        textViewngo = (TextView) findViewById(R.id.textViewngo);
        ngo1 = (Button) findViewById(R.id.ngo1);
        ngo2 = (Button) findViewById(R.id.ngo2);
        ngo3 = (Button) findViewById(R.id.ngo3);

        ngo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"+"2015bhavik.jain@ves.ac.in"));
                i.putExtra(Intent.EXTRA_SUBJECT, "Collect Excess Food");
                i.putExtra(Intent.EXTRA_TEXT, "I request you to come and collect " + tvInfo.getText().toString());
                startActivity(i);
            }
        });
        ngo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"+"2015bhavik.jain@ves.ac.in"));
                i.putExtra(Intent.EXTRA_SUBJECT, "Collect Excess Food");
                i.putExtra(Intent.EXTRA_TEXT, "I request you to come and collect " + tvInfo.getText().toString());
                startActivity(i);
            }
        });
        ngo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"+"2015bhavik.jain@ves.ac.in"));
                i.putExtra(Intent.EXTRA_SUBJECT, "Collect Excess Food");
                i.putExtra(Intent.EXTRA_TEXT, "I request you to come and collect " + tvInfo.getText().toString());
                startActivity(i);

            }
        });
    }
}

