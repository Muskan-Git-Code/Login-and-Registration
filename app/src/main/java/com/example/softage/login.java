package com.example.softage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.aviran.cookiebar2.CookieBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    EditText ed1, ed2;
    DbActivity hp;
    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        hp= new DbActivity(this);

        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);
        ed1= (EditText)findViewById(R.id.editText3);
        ed2= (EditText)findViewById(R.id.editText4);

        bt1= (Button)findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag=0;
                if(ed1.getText().toString().isEmpty()){ ed1.setError("Enter Valid Data!"); flag=1;}
                if(ed2.getText().toString().isEmpty()){ ed2.setError("Enter Valid Data!"); flag=1;}

                if(flag==0)     enter(view);
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), update.class);
                startActivity(i);
            }
        });


        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });


        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), delete.class);
                startActivity(i);
            }
        });

    }


    public void enter(View v) {
        String t1 = ed1.getText().toString();   String t2 = ed2.getText().toString();
            int a= hp.enter(t1, t2);
        if(a>0) {
            CookieBar.build(this).setIcon(R.drawable.ic_baseline_star_border_purple500_24).setTitle("Welcome to SoftAge Technologies!!").setMessage("Your have successfully Login.").setCookiePosition(CookieBar.BOTTOM).setDuration(5000).show();
            Intent i= new Intent(getApplicationContext(), home.class);
            i.putExtra("ut",t1);
            startActivity(i);
        }
        else
            CookieBar.build(this).setIcon(R.drawable.ic_baseline_star_border_purple500_24).setMessage("No Such User Account Exist !!").setCookiePosition(CookieBar.BOTTOM).setDuration(5000).show();

    }

}