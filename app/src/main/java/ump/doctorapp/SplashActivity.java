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

import ump.doctorapp.model.GlobalConstants;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_launch);

//        //clear all Hawk data
//       Hawk.deleteAll();
        if(!(Hawk.get(GlobalConstants.doctorSignTemplateKey) == null)) {
            GlobalConstants.doctorSignTemplate = Base64ToImage((String) Hawk.get(GlobalConstants.doctorSignTemplateKey));
        }


        if(!(Hawk.get(GlobalConstants.useDoctorSignTemplateStatusKey) == null)) {
            GlobalConstants.useDoctorSignTemplate = (Boolean) Hawk.get(GlobalConstants.useDoctorSignTemplateStatusKey);

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
}
