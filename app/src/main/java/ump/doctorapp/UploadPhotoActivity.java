package ump.doctorapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.widget.Toast;

import ump.doctorapp.fragment.UploadPhotoFragment;

/**
 * Created by Dionysus.Poon on 13/6/2018.
 */

public class UploadPhotoActivity extends BaseActivity {
    private static final int TakePicture_CAMERA_PERMISSION = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFrag(R.id.doctorapp_container,new UploadPhotoFragment(),getFragmentManager());
    }


    //It manipulates the result after QR Scan
    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {

            case TakePicture_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, TakePicture_CAMERA_PERMISSION);

                } else {

                    Toast.makeText(this, "Please grant Camera Permission for taking photo", Toast.LENGTH_SHORT).show();
                }
                return;

        }
    }

}
