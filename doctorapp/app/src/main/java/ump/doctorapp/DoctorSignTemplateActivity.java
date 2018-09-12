package ump.doctorapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ump.doctorapp.fragment.DoctorSignTemplateFragment;

/**
 * Created by Dionysus.Poon on 11/6/2018.
 */

public class DoctorSignTemplateActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFrag(R.id.doctorapp_container,new DoctorSignTemplateFragment(),getFragmentManager());

    }
}
