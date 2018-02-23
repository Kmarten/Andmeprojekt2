package com.example.kasutaja.andmeprojekt;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity{

        EditText UsernameEt, PasswordEt;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.login);

            UsernameEt = (EditText)findViewById(R.id.etUserName);
            PasswordEt =(EditText)findViewById(R.id.etPassword);
        }


        public void OnLogin(View view) {
            String username = UsernameEt.getText().toString();
            String password = PasswordEt.getText().toString();
            String type = "login";

            BackgroundWorker backgroundWordker = new BackgroundWorker(this);
            backgroundWordker.execute(type, username, password);
        }

}
