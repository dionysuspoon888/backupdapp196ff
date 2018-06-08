package ump.doctorapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;

import ump.doctorapp.util.LocaleManager;


/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorapp_main);
        getSupportActionBar().hide();
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
        //
    }
}
