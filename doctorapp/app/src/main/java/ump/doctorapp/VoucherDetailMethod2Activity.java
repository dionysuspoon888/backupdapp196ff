package ump.doctorapp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ump.doctorapp.fragment.VoucherDetail2Method2Fragment;
import ump.doctorapp.fragment.VoucherDetailMethod2Fragment;

/**
 * Created by Dionysus.Poon on 14/6/2018.
 */

public class VoucherDetailMethod2Activity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFrag(R.id.doctorapp_container,new VoucherDetailMethod2Fragment(),getFragmentManager());
    }
}
