package ump.doctorapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.ActivityCompat;
import android.widget.Toast;


import ump.doctorapp.fragment.VoucherDetail2Fragment;
import ump.doctorapp.fragment.VoucherList2Fragment;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class VoucherDetail2Activity extends BaseActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFrag(R.id.doctorapp_container,new VoucherDetail2Fragment(),getFragmentManager());
      }
}
