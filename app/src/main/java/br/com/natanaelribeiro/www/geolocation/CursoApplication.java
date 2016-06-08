package br.com.natanaelribeiro.www.geolocation;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by Natanael on 08/06/2016.
 */
public class CursoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}

