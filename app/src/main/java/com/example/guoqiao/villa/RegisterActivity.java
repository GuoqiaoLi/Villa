package com.example.guoqiao.villa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import beans.User;
import butterknife.Bind;
import butterknife.ButterKnife;
import helper.FireBaseHelper;


public class RegisterActivity extends AppCompatActivity {
    @Bind(R.id.first_name)
    EditText firstname;

    @Bind(R.id.last_name)
    EditText lastname;

    @Bind(R.id.password)
    EditText password;

    @Bind(R.id.re_password)
    EditText rePassword;

    @Bind(R.id.email)
    EditText email;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private FireBaseHelper FBhelper;
    private String errMsg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        FBhelper = new FireBaseHelper(this);
    }

    public void register(View view){
        progressBar.setVisibility(View.VISIBLE);

        if(validate()) {
            User user = new User(
                    email.getText().toString(),
                    password.getText().toString(),
                    firstname.getText().toString(),
                    lastname.getText().toString()
            );
            FBhelper.createUser(user);
        }
        else{

        }
    }

    public boolean validate(){
        String password1 = password.getText().toString();
        String password2 = rePassword.getText().toString();

        if(password1.length() == 0) {errMsg = "password can't be empty"; return false;}
        else if(password1.length() > 16) {errMsg = "password should be less than 16"; return false;}
        else if(!password1.equals(password2)) {errMsg = "password not same."; return false;}

        return true;
    }


}
