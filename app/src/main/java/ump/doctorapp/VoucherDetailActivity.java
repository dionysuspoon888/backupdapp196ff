package ump.doctorapp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ump.doctorapp.fragment.VoucherDetailFragment;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class VoucherDetailActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFrag(R.id.doctorapp_container,new VoucherDetailFragment(),getFragmentManager());
    }
}
