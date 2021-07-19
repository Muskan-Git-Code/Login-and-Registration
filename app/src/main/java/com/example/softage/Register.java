package com.example.softage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.aviran.cookiebar2.CookieBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    DbActivity hp;
    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        hp= new DbActivity(this);

        ed1= (EditText)findViewById(R.id.editText);
        ed2= (EditText)findViewById(R.id.editText2);
        ed3= (EditText)findViewById(R.id.editText3);
        ed4= (EditText)findViewById(R.id.editText4);



        bt1= (Button)findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int flag=0;
                if(ed1.getText().toString().isEmpty()){ ed1.setError("Enter Valid Employee Code!"); flag=1;}
                if(ed2.getText().toString().isEmpty()){ ed2.setError("Enter Valid Name!"); flag=1;}
                if(ed3.getText().toString().isEmpty() || !ed3.getText().toString().contains("@") || !ed3.getText().toString().contains(".")){ ed3.setError("Enter Valid Email Address!"); flag=1;}
                if(ed4.getText().toString().isEmpty() || !isValidPassword(ed4.getText().toString().trim())){ ed4.setError("Enter Valid Password containing minimum 8 characters at least 1 special characters, 1 upper case, 2 lower case letters!"); flag=1;}

                if(flag==0)     addUser(view);
            }
        });

    }

    public void addUser(View v) {
        String t1 = ed1.getText().toString();      String t2 = ed2.getText().toString();    String t3 = ed3.getText().toString();   String t4 = ed4.getText().toString();
            long ix = hp.insert(t1,t2,t3,t4);
            if(ix>0)
                CookieBar.build(this).setIcon(R.drawable.ic_baseline_star_border_purple500_24).setTitle("Congratulations").setMessage("Your account is successfully created.").setCookiePosition(CookieBar.BOTTOM).setDuration(5000).show();

    }


    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}