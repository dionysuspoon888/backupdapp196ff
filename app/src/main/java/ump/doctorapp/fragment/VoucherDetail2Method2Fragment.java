package ump.doctorapp.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v13.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import ump.doctorapp.R;
import ump.doctorapp.VoucherActivity;
import ump.doctorapp.VouchereSignatureActivity;
import ump.doctorapp.model.GlobalConstants;

/**
 * Created by Dionysus.Poon on 14/6/2018.
 */

public class VoucherDetail2Method2Fragment extends BaseFragment {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    ImageView iv_doctorapp_patientsignature;
    ImageView iv_doctorapp_doctorsignature;
    Button b_evoucher_submit;
    LinearLayout ll_evoucher_scan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_voucherdetail2method2,container,false);
        iv_doctorapp_patientsignature = v.findViewById(R.id.iv_doctorapp_patientsignature);
        iv_doctorapp_doctorsignature = v.findViewById(R.id.iv_doctorapp_doctorsignature);
        b_evoucher_submit = v.findViewById(R.id.b_evoucher_submit);
        ll_evoucher_scan = v.findViewById(R.id.ll_evoucher_scan);

        iv_doctorapp_patientsignature.setImageBitmap(GlobalConstants.eVoucherPatientSignatureTreeMap.get("0"));
        iv_doctorapp_doctorsignature.setImageBitmap(GlobalConstants.eVoucherDoctorSignatureTreeMap.get("0"));
       GlobalConstants.eVoucherMethod2SnapShot =  takeScreenShot(ll_evoucher_scan);

        b_evoucher_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyStoragePermissions(getActivity());
            }
        });


        return v;
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity the activity from which permissions are checked
     */
    public void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }else{
//            Log.i("56123","I am in verifyStoragePermissions SS");
//            Toast.makeText(getActivity(), "SSSSSSSSSSSSSSSSSSSSSS", Toast.LENGTH_SHORT).show();

            if (addSignatureToGallery(GlobalConstants.eVoucherMethod2SnapShot)) {

                String doctorapp_scanss = getString(R.string.doctorapp_scanss);
                Toast.makeText(getActivity(), doctorapp_scanss, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),VoucherActivity.class));
            } else {
                String doctorapp_scanff = getString(R.string.doctorapp_scanff);
                Toast.makeText(getActivity(), doctorapp_scanff, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Bitmap takeScreenShot(View view) {
        Log.i("takeScreenShot","Running");
        // configuramos para que la view almacene la cache en una imagen
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);

        //fix view.getDrawingCache null in small screen size(480*800)
        // this is the important code :)
        // Without it the view will have a dimension of 0,0 and the bitmap will be null
        if(GlobalConstants.width <= 480 && GlobalConstants.height <= 800) {
            view.measure(View.MeasureSpec.makeMeasureSpec(GlobalConstants.width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(GlobalConstants.height, View.MeasureSpec.EXACTLY));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }

        view.buildDrawingCache();

        if(view.getDrawingCache() == null) {
            Log.i("takeScreenShot","view.getDrawingCache() == null");
            return null; // Verificamos antes de que no sea null
        }

        // utilizamos esa cache, para crear el bitmap que tendra la imagen de la view actual
        Bitmap snapshot = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();

        Log.i("takeScreenShot","snapshot");
        return snapshot;
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

    public void saveBitmap(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
        stream.close();
    }

    public boolean addSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()));
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

    public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String BitmapToBase64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return encodedString;
    }


    public Bitmap Base64ToImage(String inputBase64Image){


        //Base64 Image decode
        byte[] decodedString = Base64.decode(inputBase64Image, Base64.DEFAULT);
        Bitmap base64Image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        // iv_testing.setImageBitmap(decodedByte);
        return base64Image;
    }
}
