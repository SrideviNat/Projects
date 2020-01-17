package com.example.adrianna.fitnesspredictionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    private DataBaseHelper helper=new DataBaseHelper(this);
    private EditText userUsersName, userPassword, userName;
    private Button registor;
    private TextView returnToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setUpView();
        registor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userUsersName.getText().toString();
                String password = userPassword.getText().toString();
                String name = Registration.this.userName.getText().toString();
                if(validateDate(userName,password,name)){
                    User user=new User();
                    user.setUserName(userName);
                    user.setName(name);
                    user.setPassword(password);

                    helper.addUser(user);
                }
            }
        });
        returnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,LocalLogin.class));
            }
        });
    }

    //to connect each id from the xml file, to there corresponding java id
    private void setUpView() {
        userUsersName = (EditText) findViewById(R.id.regUserName);
        userPassword = (EditText) findViewById(R.id.etPassword);
        userName = (EditText) findViewById(R.id.etName);
        registor = (Button) findViewById(R.id.btnRegistor);
        returnToLogin = (TextView) findViewById(R.id.tvReturnToLoginPage);
    }

    //checking if the user has entered something in every category
    private boolean validateDate(String userName, String password, String name) {
        boolean result = false;

        if (userName.isEmpty() || password.isEmpty() || name.isEmpty()) {
            /*Displaying an error message:
             * 1. this is refering to Registration activity
             * 2. the error message
             * 3.time duration in which the error message should be displayed
             */
            Toast.makeText(this, "Please enter all your details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
    

}
