package ump.doctorapp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ump.doctorapp.fragment.VouchereSignatureFragment;

/**
 * Created by Dionysus.Poon on 14/6/2018.
 */

public class VouchereSignatureActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFrag(R.id.doctorapp_container,new VouchereSignatureFragment(),getFragmentManager());
    }
}
