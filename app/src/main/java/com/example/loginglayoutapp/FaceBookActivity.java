package com.example.loginglayoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class FaceBookActivity extends AppCompatActivity {

    TextView txtStatus;
    LoginButton fb_login_button;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_face_book);
        initializeControls();
        loginWithFb();



    }

    private void initializeControls()
    {
        callbackManager=CallbackManager.Factory.create();
        txtStatus=findViewById(R.id.txtstatus);
        fb_login_button=findViewById(R.id.fb_login_button);
    }

    private void loginWithFb() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                txtStatus.setText("Login Success" + loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                txtStatus.setText("Login Cancelled");

            }

            @Override
            public void onError(FacebookException error) {
                txtStatus.setText("Login Error" + error.getMessage());
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}