package ump.doctorapp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ump.doctorapp.fragment.UploadPhotoHistoryFragment;

/**
 * Created by Dionysus.Poon on 13/6/2018.
 */

public class UploadPhotoHistoryActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFrag(R.id.doctorapp_container,new UploadPhotoHistoryFragment(),getFragmentManager());
    }
}
