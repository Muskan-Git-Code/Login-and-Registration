package com.example.softage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();
        TextView tv1= (TextView)findViewById(R.id.textView);
        Intent i=getIntent();
        tv1.setText("Welcome  "+ i.getStringExtra("ut"));

    }
}