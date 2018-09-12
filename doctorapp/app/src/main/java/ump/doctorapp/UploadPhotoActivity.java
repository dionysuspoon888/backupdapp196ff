package ump.doctorapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import ump.doctorapp.fragment.UploadPhotoFragment;
import ump.doctorapp.model.GlobalConstants;

/**
 * Created by Dionysus.Poon on 13/6/2018.
 */

public class UploadPhotoActivity extends BaseActivity {
    private static final int TakePicture2_CAMERA_PERMISSION = 4;
    public Uri imageUri;
    public Bitmap photoBitmap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFrag(R.id.doctorapp_container,new UploadPhotoFragment(),getFragmentManager());
    }


    //It manipulates the result after QR Scan
    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {

            case TakePicture2_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, TakePicture2_CAMERA_PERMISSION);

                } else {


                    String tmpGrantCameraPermission = getString(R.string.doctorapp_photo_grantcamerapermission);
                    Toast.makeText(this, tmpGrantCameraPermission, Toast.LENGTH_SHORT).show();
                }
                return;

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //load it to our apps wih ID PICK_IMAGE_REQUEST
        super.onActivityResult(requestCode, resultCode, data);



         if (requestCode == TakePicture2_CAMERA_PERMISSION && resultCode == Activity.RESULT_OK  && data != null && data.getData() != null) {

            imageUri = data.getData();

            try {

                Fragment frag = new UploadPhotoFragment();


                photoBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                ((ImageView) frag.getView().findViewById(R.id.iv_doctorapp_imagechosen)).setImageBitmap(photoBitmap);
                UploadPhotoFragment.rounter = "fromChooseImageFile";

            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("CameraDemo", "Pic saved");

        }
    }

}
