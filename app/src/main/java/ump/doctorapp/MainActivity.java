package ump.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ump.doctorapp.fragment.MainFragment;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFrag(R.id.doctorapp_container,new MainFragment(),getFragmentManager());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
