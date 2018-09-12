package ump.doctorapp.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import ump.doctorapp.R;
import ump.doctorapp.VoucherActivity;
import ump.doctorapp.model.GlobalConstants;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class VoucherDetailFragment extends BaseFragment {

    public SignaturePad patient_sign;
    public SignaturePad doctor_sign;

    public Button b_evoucher_clear_patientsign;
    public Button b_evoucher_clear_doctorsign;
    public Button b_evoucher_submit;

    public Boolean patientsignsStatus;
    public Boolean doctorsignsStatus;

    public LinearLayout ll_evoucher_scan;

    public ImageView iv_doctor_sign;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_voucherdetail,container,false);

//
        //init
        patientsignsStatus = false;
        doctorsignsStatus = false;

        if(GlobalConstants.useDoctorSignTemplate == true && GlobalConstants.doctorSignTemplate != null){
            doctorsignsStatus = true;
        }else{
            doctorsignsStatus = false;
        }

        patient_sign = (SignaturePad) v.findViewById(R.id.patient_sign);
        doctor_sign =(SignaturePad) v.findViewById(R.id.doctor_sign);
        b_evoucher_clear_patientsign = v.findViewById(R.id.b_evoucher_clear_patientsign);
        b_evoucher_clear_doctorsign = v.findViewById(R.id.b_evoucher_clear_doctorsign);
        b_evoucher_submit = v.findViewById(R.id.b_evoucher_submit);

        ll_evoucher_scan = v.findViewById(R.id.ll_evoucher_scan);
        iv_doctor_sign = v.findViewById(R.id.iv_doctor_sign);

        if(GlobalConstants.useDoctorSignTemplate == true && GlobalConstants.doctorSignTemplate != null){
                doctor_sign.setVisibility(View.GONE);
                b_evoucher_clear_doctorsign.setVisibility(View.GONE);
                iv_doctor_sign.setImageBitmap(GlobalConstants.doctorSignTemplate);
                iv_doctor_sign.setVisibility(View.VISIBLE);

        }else{
            doctor_sign.setVisibility(View.VISIBLE);
            b_evoucher_clear_doctorsign.setVisibility(View.VISIBLE);
            iv_doctor_sign.setVisibility(View.GONE);

        }


        patient_sign.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                patientsignsStatus = true;
            }

            @Override
            public void onSigned() {
                patientsignsStatus = true;

            }

            @Override
            public void onClear() {
                patientsignsStatus = false;

            }
        });

        doctor_sign.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                doctorsignsStatus = true;
            }

            @Override
            public void onSigned() {
                doctorsignsStatus = true;


            }

            @Override
            public void onClear() {
                doctorsignsStatus = false;


            }
        });


        b_evoucher_clear_patientsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient_sign.clear();
            }
        });

        b_evoucher_clear_doctorsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               doctor_sign.clear();
            }
        });

        b_evoucher_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(patientsignsStatus == true && doctorsignsStatus == true) {

                    GlobalConstants.eVoucherDataTreeMap = new TreeMap<>();
                    GlobalConstants.eVoucherDataTreeMap.put("0",takeScreenShot(ll_evoucher_scan));

                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date date = new Date();
                    GlobalConstants.signingDateTable.put("0", dateFormat.format(date));


                    GlobalConstants.eSignatureStatus = true;

                    String eSignatureSubmit = "eSignature Submitted";
                    if (isAdded()) {
                        eSignatureSubmit = getString(R.string.voucher_esignaturesubmitted);
                    }

                   Toast.makeText(getActivity().getBaseContext(), eSignatureSubmit, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), VoucherActivity.class));

                }else{
                    String tmpeSignaturePleaseSign = "請簽署";
                    if (isAdded()) {
                        tmpeSignaturePleaseSign = getString(R.string.voucher_esignaturepleasesign);
                    }
                    Toast.makeText(getActivity().getBaseContext(), tmpeSignaturePleaseSign, Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;
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
