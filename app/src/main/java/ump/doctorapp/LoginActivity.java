package ump.doctorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ump.doctorapp.fragment.LoginFragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startFrag(R.id.doctorapp_container,new LoginFragment(),getFragmentManager());

    }
}
