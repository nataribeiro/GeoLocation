package br.com.natanaelribeiro.www.geolocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);

        if (loginButton != null) {
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.e("CURSO", "toString: " + loginResult.toString());
                    Log.e("CURSO", "accesstoken: " + loginResult.getAccessToken());
                    Log.e("CURSO", "token: " + loginResult.getAccessToken().getToken());
                    Log.e("CURSO", "granted: " + loginResult.getRecentlyGrantedPermissions());
                    Log.e("CURSO", "denied: " + loginResult.getRecentlyDeniedPermissions());
                }

                @Override
                public void onCancel() {}

                @Override
                public void onError(FacebookException error) {}
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
