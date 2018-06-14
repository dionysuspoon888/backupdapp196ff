package ump.doctorapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;

import ump.doctorapp.model.GlobalConstants;
import ump.doctorapp.model.Voucher1Data;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_launch);
        initData();


         //clear all Hawk data
      // Hawk.deleteAll();

        if(!(Hawk.get(GlobalConstants.doctorSignTemplateKey) == null)) {
            GlobalConstants.doctorSignTemplate = Base64ToImage((String) Hawk.get(GlobalConstants.doctorSignTemplateKey));
        }


        if(!(Hawk.get(GlobalConstants.useDoctorSignTemplateStatusKey) == null)) {
            GlobalConstants.useDoctorSignTemplate = (Boolean) Hawk.get(GlobalConstants.useDoctorSignTemplateStatusKey);

        }


        if(Hawk.get(GlobalConstants.useeSignMethodKey) == null) {
          Hawk.put(GlobalConstants.useeSignMethodKey,true);
        }



        // Decide which screen to be shown on launch
        View activity_app_launch = findViewById(R.id.activity_app_launch);
//
        // Animation
        AlphaAnimation alphaAnim;
        final float fromAlpha = 0.1f;
        final float toAlpha = 1.0f;
        final int anim_time = 2100;

        alphaAnim = new AlphaAnimation(fromAlpha, toAlpha);
        alphaAnim.setDuration(anim_time);
        activity_app_launch.startAnimation(alphaAnim);

        alphaAnim.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                animEndOperation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
//
            }

            @Override
            public void onAnimationStart(Animation animation) {

            }
        });
    }

    private void animEndOperation() {


        final Class<? extends Activity> activityClass;
        activityClass = LoginActivity.class;
        startActivity(new Intent(this, activityClass));
        finish();
    }

    public void initData(){
        GlobalConstants.eVoucherDataTreeMap = new TreeMap<>();
        GlobalConstants.eSignatureStatus = false;

        GlobalConstants.useDoctorSignTemplate = false;
        GlobalConstants.doctorSignTemplate = null;
        GlobalConstants.bitmap = null;
        GlobalConstants.Base64String = "";
        GlobalConstants.size = 0;

        GlobalConstants.Voucher1DetailList = new ArrayList<>();

        GlobalConstants.voucherListpositionClicked = "";

        GlobalConstants.eSignatureTable = new TreeMap<>();
        GlobalConstants.signingDateTable = new Hashtable<>();

        GlobalConstants.photoBitmap = null;
        GlobalConstants.UploadPhotoDataList = new ArrayList<>();
        GlobalConstants.PhotoHistoryClickedPosition = 0;
        GlobalConstants.takePhotoCount = 0;

    }
}
