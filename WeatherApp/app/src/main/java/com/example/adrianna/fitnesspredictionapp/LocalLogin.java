package com.example.adrianna.fitnesspredictionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class LocalLogin extends AppCompatActivity {
    private EditText userName;
    private EditText password;
    private TextView info;
    private Button login;
    private int mismatchCounter=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_login);
        userName=(EditText)findViewById(R.id.genericLoginName);
        password=(EditText)findViewById(R.id.genericLoginPassword);
        info=(TextView)findViewById(R.id.incorrectLoginAttempts);
        login=(Button)findViewById(R.id.btnLogin);
        info.setText("number of attempts remaining: "+String.valueOf(mismatchCounter));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(userName.getText().toString(),password.getText().toString());
                
            }
        });

    }
    private void validate(String userName, String password){
        DataBaseHelper helper=new DataBaseHelper(this);
        String passW=helper.searchUser(userName);
        if(password.equals(passW)){
             //Intent is to move from one activity to another
             Intent intent = new Intent(LocalLogin.this,HomePage.class);
             startActivity(intent);
        }else if(passW.equals("not found")){
            Toast.makeText(this, "This user has not been registered yet", Toast.LENGTH_SHORT).show();
        }else{
            mismatchCounter--;

             info.setText("number of attempts remaining: "+String.valueOf(mismatchCounter));

            if(mismatchCounter==0){
                login.setEnabled(false);
            }
        }
    }
}
