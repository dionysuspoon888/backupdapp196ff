package ump.doctorapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.orhanobut.hawk.Hawk;

import java.io.ByteArrayOutputStream;

import ump.doctorapp.util.LocaleManager;


/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class BaseActivity extends AppCompatActivity {

    public Button back_btn;
    public RelativeLayout rl_doctorapp_topbar;
    public String TAG = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hawk.init(this).build();
        setContentView(R.layout.activity_doctorapp_main);
        getSupportActionBar().hide();
        if(this.getClass() != null) {
            TAG = this.getClass().getSimpleName();
        }
        back_btn = findViewById(R.id.back_btn);
        rl_doctorapp_topbar = findViewById(R.id.rl_doctorapp_topbar);

        if(TAG.equals("LoginActivity")){
            rl_doctorapp_topbar.setVisibility(View.GONE);
        }else{
            rl_doctorapp_topbar.setVisibility(View.VISIBLE);
        }
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    //start the fragment without back stack (if clicked back, it would not go back to this fragment)
    public static void startFrag(int container, Fragment fragment, FragmentManager fm) {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(container, fragment)
                .commit();

    }

    //start the fragment with back stack (if clicked back, it would go back to this fragment)
    public static void startFragBackState(int container, Fragment fragment, FragmentManager fm) {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(container, fragment)
                .addToBackStack(null)
                .commit();

    }

    //feature , load config to determine lang and set lang for all page.(ALL Activity requires it)
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));

    }

    public String BitmapToBase64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return encodedString;
    }


    public Bitmap Base64ToImage(String inputBase64Image){


        //Base64 Image decode
        byte[] decodedString = Base64.decode(inputBase64Image, Base64.DEFAULT);
        Bitmap base64Image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        // iv_testing.setImageBitmap(decodedByte);
        return base64Image;
    }


}
