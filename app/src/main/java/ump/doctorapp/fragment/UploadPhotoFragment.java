package ump.doctorapp.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ump.doctorapp.R;
import ump.doctorapp.UploadPhotoHistoryActivity;
import ump.doctorapp.model.GlobalConstants;
import ump.doctorapp.model.UploadPhotoData;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Dionysus.Poon on 13/6/2018.
 */

public class UploadPhotoFragment extends BaseFragment {
    //ID for request
    private static final int PICK_IMAGE_REQUEST = 2;
    private static final int TakePicture_CAMERA_PERMISSION = 3;
    public static final int REQUEST_CAMERA = 4;

    public Button b_doctorapp_takephoto;
    public Button b_doctorapp_chooseimagefile;
    public Button b_doctorapp_photo_upload;
    public EditText et_doctorapp_photo_name;
    public EditText et_doctorapp_photo_remark;
    public ImageView iv_doctorapp_imagechosen;
    public ProgressBar pb_doctorapp_photo;
    public TextView tv_doctorapp_photo_showuploads;




    public Uri takePhotoUri;
    public Bitmap takePhotoBitmap;

    public Uri imageUri;
    public Bitmap photoBitmap;

     public String rounter = "";

     public  String imageFileNameText;


    //Control UploadTask
    //private StorageTask uploadTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doctorapp_uploadphoto,container,false);
        b_doctorapp_takephoto = v.findViewById(R.id.b_doctorapp_takephoto);
        b_doctorapp_chooseimagefile =  v.findViewById(R.id.b_doctorapp_chooseimagefile);
        b_doctorapp_photo_upload =  v.findViewById(R.id.b_doctorapp_photo_upload);
        et_doctorapp_photo_name = v.findViewById(R.id.et_doctorapp_photo_name);
        et_doctorapp_photo_remark =  v.findViewById(R.id.et_doctorapp_photo_remark);
        iv_doctorapp_imagechosen =  v.findViewById(R.id.iv_doctorapp_imagechosen);
        pb_doctorapp_photo =  v.findViewById(R.id.pb_doctorapp_photo);
        tv_doctorapp_photo_showuploads = v.findViewById(R.id.tv_doctorapp_photo_showuploads);



        b_doctorapp_takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA}, TakePicture_CAMERA_PERMISSION);

                }else {

                   // String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String imageFileName = "TakePhoto_%d" + System.currentTimeMillis();

                    imageFileNameText =  String.format("TakePhoto_%d.jpg", System.currentTimeMillis());
                    File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File imageFile = null;
                    try {
                        imageFile = File.createTempFile(
                                imageFileName,  /* prefix */
                                ".jpg",   /* suffix */
                                storageDir      /* directory */
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.i("4316", imageFile.toString());


                    Uri photoUri = FileProvider.getUriForFile(
                           getActivity(),
                            "ump.doctorapp.fileprovider",
                            imageFile);
                    takePhotoUri = photoUri;

                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                     cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(cameraIntent, TakePicture_CAMERA_PERMISSION);

                }



            }
        });



        b_doctorapp_chooseimagefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        b_doctorapp_photo_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadFile();

//                //Check UploadTask currently running
//                if (uploadTask != null && uploadTask.isInProgress()) {
//                    Toast.makeText(FirebaseActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
//                } else {
//                    uploadFile();
//                }
            }
        });

        tv_doctorapp_photo_showuploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagesActivity();
            }
        });

        return v;
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    private void openFileChooser() {
        //Open File Chooser to choose image file
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //Set ID PICK_IMAGE_REQUEST
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //load it to our apps wih ID PICK_IMAGE_REQUEST
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();

            try {
                photoBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                iv_doctorapp_imagechosen.setImageBitmap(photoBitmap);
                rounter = "fromChooseImageFile";

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if (requestCode == TakePicture_CAMERA_PERMISSION && resultCode == Activity.RESULT_OK) {

            try {
                takePhotoBitmap  = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), takePhotoUri);
                iv_doctorapp_imagechosen.setImageBitmap(takePhotoBitmap);
                addTakePhotoToGallery(takePhotoBitmap,imageFileNameText);

               GlobalConstants.takePhotoCount++;
                rounter = "fromTakePicture";


            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("CameraDemo", "Pic saved");

        }
    }
    //Get the File Extension e.g. jpeg,bmp
    private String getFileExtension(Uri uri) {

        ContentResolver cR = getActivity().getContentResolver();
        //Mime=Multipurpose Internet Mail Extensions e.g.image/jpeg
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private void uploadFile() {
        if (imageUri != null ||takePhotoUri != null) {

            Bitmap tmpbitmap = ((BitmapDrawable)iv_doctorapp_imagechosen.getDrawable()).getBitmap();
            String photoName = "";
            if(rounter.equals("fromChooseImageFile")){
                photoName = getFileName(imageUri);
            }else if(rounter.equals("fromTakePicture")) {
                photoName = imageFileNameText;
                //photoName = "tmpPhoto_"+GlobalConstants.takePhotoCount+getFileExtension(takePhotoUri);
            }
           // String photoName = et_doctorapp_photo_name.getText().toString().trim();
            String remark = et_doctorapp_photo_remark.getText().toString().trim();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String uploadDate =  dateFormat.format(date);

            GlobalConstants.UploadPhotoDataList.add(new UploadPhotoData(tmpbitmap,photoName,remark,uploadDate));
            Toast.makeText(getActivity(), "Upload Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    //Show upload folder
    private void openImagesActivity() {
        Intent intent = new Intent(getActivity(), UploadPhotoHistoryActivity.class);
        startActivity(intent);
    }




    //modified
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {

        }
    }


    public void saveBitmap(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
        stream.close();
    }

    public boolean addTakePhotoToGallery(Bitmap signature,String fileName) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("TakePhoto"), fileName);
            saveBitmap(signature, photo);
            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }
}
