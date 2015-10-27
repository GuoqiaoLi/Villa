package com.example.guoqiao.villa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

import helper.FireBaseHelper;

public class LoginActivity extends AppCompatActivity {
    private Firebase myRef;
    TextView login;
    EditText username;
    EditText password;

    private FireBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        init();
    }

    public void init(){
        helper = new FireBaseHelper(this);
    }

    public void login(View view){

    }

    public void register(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void forget(View view){

    }






}
