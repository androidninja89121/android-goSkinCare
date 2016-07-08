package com.goskincare.application;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Morning on 11/24/2015.
 */
public class GoSkinCareApplication extends Application {

    public static final String TAG = GoSkinCareApplication.class.getSimpleName();

    private static GoSkinCareApplication _instance;

    @Override
    public void onCreate() {

        super.onCreate();
        _instance = this;

        getUserInfo();
    }

    /**
     *  return application instance
     * @return
     */
    public static synchronized GoSkinCareApplication getInstance() {
        return _instance;
    }


    // get sha1 and then print logo
    public void getUserInfo(){
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
