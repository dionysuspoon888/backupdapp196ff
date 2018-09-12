package ump.doctorapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

import ump.doctorapp.model.GlobalConstants;
import ump.doctorapp.util.LocaleManager;


/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class BaseActivity extends AppCompatActivity {

    public Button back_btn;
    public RelativeLayout rl_doctorapp_topbar;
    public String TAG = "";

    TextView tv_membercard_toptitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hawk.init(this).build();
        setContentView(R.layout.activity_doctorapp_main);
        getSupportActionBar().hide();

        //Ensure Lang
        Log.i("5699888","G.Lang is "+GlobalConstants.Lang);
        Log.i("5699888","Sys.Lang is "+getResources().getConfiguration().locale.getLanguage().toString());
        if(GlobalConstants.Lang.equals("en")){
            LocaleManager.setNewLocale(this, Locale.ENGLISH);
        }else if(GlobalConstants.Lang.equals("zh")){
            LocaleManager.setNewLocale(this, Locale.CHINESE);
        }
        Log.i("5699888","Sys.Lang after is "+getResources().getConfiguration().locale.getLanguage().toString());

        tv_membercard_toptitle = findViewById(R.id.tv_membercard_toptitle);
        if(this.getClass() != null) {
            TAG = this.getClass().getSimpleName();
        }
        back_btn = findViewById(R.id.back_btn);
        rl_doctorapp_topbar = findViewById(R.id.rl_doctorapp_topbar);

        if(TAG.equals("LoginActivity")){
            rl_doctorapp_topbar.setVisibility(View.GONE);
        }else{
            rl_doctorapp_topbar.setVisibility(View.VISIBLE);
        }
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Get the screen side of current mobile phone width & height)
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        GlobalConstants.width = width;
        GlobalConstants.height = height;
    }

    @Override
    public void onResume() {
        super.onResume();
         //Ensure Lang
        Log.i("5699888","G.Lang is "+GlobalConstants.Lang);
        Log.i("5699888","Sys.Lang is "+getResources().getConfiguration().locale.getLanguage().toString());
        if(GlobalConstants.Lang.equals("en")){
            LocaleManager.setNewLocale(this, Locale.ENGLISH);
        }else if(GlobalConstants.Lang.equals("zh")){
            LocaleManager.setNewLocale(this, Locale.CHINESE);
        }
        Log.i("5699888","Sys.Lang after is "+getResources().getConfiguration().locale.getLanguage().toString());

        //dynamically set Title for different page
        //set after onCreate to ensure Fragment has already started and updated GlobalConstants.Location & for kill activity case
        // Log.i("777","DO I Run ?"+"SS "+ TAG);

        switch (TAG) {
            case "VoucherActivity":
                tv_membercard_toptitle.setText(R.string.doctorapp_medicalvoucher_title);
                break;

            case "VoucherDetailActivity":
                tv_membercard_toptitle.setText(R.string.doctorapp_medicalvoucher_title);
                break;
            case "VoucherDetail2Activity":
                tv_membercard_toptitle.setText(R.string.doctorapp_medicalvoucher_title);
                break;
            case "VoucherDetailMethod2Activity":
                tv_membercard_toptitle.setText(R.string.doctorapp_medicalvoucher_title);
                break;
            case "VouchereSignatureActivity":
                tv_membercard_toptitle.setText(R.string.doctorapp_esignature_title);
                break;
            case "VoucherDetail2Method2Activity":
                tv_membercard_toptitle.setText(R.string.doctorapp_medicalvoucher_title);
                break;
            case "DoctorSignTemplateActivity":
                tv_membercard_toptitle.setText(R.string.doctorapp_signaturetemplate_title);
                break;
            case "Setting":
                tv_membercard_toptitle.setText(R.string.doctorapp_setting_title);
                break;

            case "UploadDocumentPhotoActivity":
                tv_membercard_toptitle.setText(R.string.doctorapp_uploaddocumentphoto_title);
                break;

            case "UploadPhotoActivity":
                tv_membercard_toptitle.setText(R.string.doctorapp_uploaddocumentphoto_title);
                break;

            case "UploadPhotoHistoryActivity":
                tv_membercard_toptitle.setText(R.string.doctorapp_uploadphotohistory_title);
                break;

            case "UploadPhotoDetailActivity":
                tv_membercard_toptitle.setText(R.string.doctorapp_uploadphotohistory_title);
                break;

            default:
                tv_membercard_toptitle.setText("UMP DOCTOR APP");
                break;
        }
    }

    //start the fragment without back stack (if clicked back, it would not go back to this fragment)
    public static void startFrag(int container, Fragment fragment, FragmentManager fm) {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(container, fragment)
                .commit();

    }

    //start the fragment with back stack (if clicked back, it would go back to this fragment)
    public static void startFragBackState(int container, Fragment fragment, FragmentManager fm) {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(container, fragment)
                .addToBackStack(null)
                .commit();

    }

    //feature , load config to determine lang and set lang for all page.(ALL Activity requires it)
    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));

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

    //For Validation
    public void alertCenterStyle(String alertMessage,Activity activity){
        // String a = getString(R.string.validate_positive_amount_paid_by_other);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.attention_alert_title);

        //builder.setMessage(R.string.validate_positive_amount_paid_by_other);
        builder.setMessage(alertMessage);

        builder.setPositiveButton(R.string.disclaimer_ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();

        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);

        TextView titleView = (TextView)dialog.findViewById(getResources().getIdentifier("alertTitle", "id", "android"));
        if (titleView != null) {
            titleView.setGravity(Gravity.CENTER);
        }
        final Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        //LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
        LinearLayout.LayoutParams positiveButtonLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        positiveButtonLL.gravity = Gravity.CENTER;
        positiveButton.setLayoutParams(positiveButtonLL);

        return;

    }

    //Custom alertDialog with custom message
    public void alertCenterStyle(int alertMessage,Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.attention_alert_title);

        //builder.setMessage(R.string.validate_positive_amount_paid_by_other);
        builder.setMessage(alertMessage);

        builder.setPositiveButton(R.string.disclaimer_ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();

        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);

        TextView titleView = (TextView)dialog.findViewById(getResources().getIdentifier("alertTitle", "id", "android"));
        if (titleView != null) {
            titleView.setGravity(Gravity.CENTER);
        }
        final Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        //LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
        LinearLayout.LayoutParams positiveButtonLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        positiveButtonLL.gravity = Gravity.CENTER;
        positiveButton.setLayoutParams(positiveButtonLL);

        return;

    }


}
