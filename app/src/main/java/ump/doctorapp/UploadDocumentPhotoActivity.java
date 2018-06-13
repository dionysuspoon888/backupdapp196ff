package ump.doctorapp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ump.doctorapp.fragment.UploadDocumentPhotoFragment;

/**
 * Created by Dionysus.Poon on 12/6/2018.
 */

public class UploadDocumentPhotoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFrag(R.id.doctorapp_container,new UploadDocumentPhotoFragment(),getFragmentManager());
        

    }
}
