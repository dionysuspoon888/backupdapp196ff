package ump.doctorapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import java.util.Locale;

import ump.doctorapp.model.GlobalConstants;
import ump.doctorapp.util.CustomRadioButton;
import ump.doctorapp.util.LocaleManager;


/**
 * Created by Dionysus.Poon on 11/6/2018.
 */

public class SettingActivity extends BaseActivity {

    public static final String TAG = "UMP";
    public static final String SHOW_TUTORIAL = "SHOW_TUTORIAL";

    public static Editor editor = null;


    private Resources resources;

    //private static Button back_btn;



    private RadioGroup access_code_language_group;
    private CustomRadioButton access_code_language_zh;
    private CustomRadioButton access_code_language_en;


    public RadioGroup rg_doctorapp_usedoctorsignaturetemplate;
    public CustomRadioButton rb_doctorapp_usedoctorsignaturetemplate_yes;
    public CustomRadioButton rb_doctorapp_usedoctorsignaturetemplate_no;


    private TextView ump_version_num;

    public static boolean cur_language_zh = true;
    public static boolean cur_show_tutorial_zh = true;


    private String tempLanguageConfig = "";
    public static SharedPreferences myPreference = null;


    private int sdk_version;

    public TextView tv_language;
    public TextView tv_use_doctorsignature_template;

    public Button back_btn;

    public Button b_setting_logout_real;

    public boolean useDoctorSignTemplate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorapp_setting);


        myPreference = this.getSharedPreferences(TAG, Context.MODE_PRIVATE);

        //init
      if(!(Hawk.get(GlobalConstants.useDoctorSignTemplateStatusKey) == null)) {
          useDoctorSignTemplate = Hawk.get(GlobalConstants.useDoctorSignTemplateStatusKey);
      }else{
          useDoctorSignTemplate = false;
      }


        sdk_version = android.os.Build.VERSION.SDK_INT;




        access_code_language_group = findViewById(R.id.main_language_group);
        access_code_language_zh = findViewById(R.id.access_code_language_zh);
        access_code_language_en = findViewById(R.id.access_code_language_en);

        rg_doctorapp_usedoctorsignaturetemplate = findViewById(R.id.rg_doctorapp_usedoctorsignaturetemplate);
        rb_doctorapp_usedoctorsignaturetemplate_yes = findViewById(R.id.rb_doctorapp_usedoctorsignaturetemplate_yes);
        rb_doctorapp_usedoctorsignaturetemplate_no = findViewById(R.id.rb_doctorapp_usedoctorsignaturetemplate_no);

        ump_version_num = findViewById(R.id.ump_version_num);

        tv_language = findViewById(R.id.tv_language);
        tv_use_doctorsignature_template = findViewById(R.id.tv_use_doctorsignature_template);
        back_btn = findViewById(R.id.back_btn);
        b_setting_logout_real = findViewById(R.id.b_setting_logout_real);


        access_code_language_zh.setButtonDrawable(new ColorDrawable(
                Color.TRANSPARENT));
        access_code_language_en.setButtonDrawable(new ColorDrawable(
                Color.TRANSPARENT));

        rb_doctorapp_usedoctorsignaturetemplate_yes.setButtonDrawable(new ColorDrawable(
                Color.TRANSPARENT));
        rb_doctorapp_usedoctorsignaturetemplate_no.setButtonDrawable(new ColorDrawable(
                Color.TRANSPARENT));

        resources = getResources();


        if (tempLanguageConfig.equals(Locale.CHINESE.toString())) {
            // TODO
            access_code_language_en.startAnimation(CustomRadioButton.alpha_to_half);
            cur_language_zh = true;
            setLanguage(cur_language_zh);
        } else if (tempLanguageConfig.equals(Locale.ENGLISH.toString())) {
            // TODO
            access_code_language_zh.startAnimation(CustomRadioButton.alpha_to_half);
            cur_language_zh = false;
            setLanguage(cur_language_zh);
        }


        if (useDoctorSignTemplate)  {
            // TODO
            rb_doctorapp_usedoctorsignaturetemplate_yes.startAnimation(CustomRadioButton.alpha_to_half);
            cur_show_tutorial_zh = true;
            setShowTutorial(cur_show_tutorial_zh);

        } else {
            // TODO
            rb_doctorapp_usedoctorsignaturetemplate_no.startAnimation(CustomRadioButton.alpha_to_half);
            cur_show_tutorial_zh = false;
            setShowTutorial(cur_show_tutorial_zh);
        }


        access_code_language_zh.setOnClickListener(v -> {

            if (!cur_language_zh) {
                cur_language_zh = true;
                setLanguage(cur_language_zh);
            }
        });

        access_code_language_en.setOnClickListener(v -> {

            if (cur_language_zh) {
                cur_language_zh = false;
                setLanguage(cur_language_zh);
            }
        });

        rb_doctorapp_usedoctorsignaturetemplate_yes.setOnClickListener(v -> {

            if (!cur_show_tutorial_zh) {
                cur_show_tutorial_zh = true;

                GlobalConstants.useDoctorSignTemplate = true;
                Hawk.put(GlobalConstants.useDoctorSignTemplateStatusKey,GlobalConstants.useDoctorSignTemplate);

                setShowTutorial(cur_show_tutorial_zh);

                Log.i(TAG, "SettingActivity show_tutorial_zh.setOnClickListener");
            }
        });

        rb_doctorapp_usedoctorsignaturetemplate_no.setOnClickListener(v -> {

            if (cur_show_tutorial_zh) {
                cur_show_tutorial_zh = false;

                GlobalConstants.useDoctorSignTemplate = false;
                Hawk.put(GlobalConstants.useDoctorSignTemplateStatusKey,GlobalConstants.useDoctorSignTemplate);

                setShowTutorial(cur_show_tutorial_zh);

                Log.i(TAG, "SettingActivity show_tutorial_en.setOnClickListener");
            }
        });



        b_setting_logout_real.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),LoginActivity.class));
                finish();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO
        // MyUtils.setFullScreen(SettingActivity.this);

        //tempPrefs_Access_Code_Medical = myPreference.getString(
        //         ACCESS_CODE_PREFERENCE_NAME_MEDICAL, "");

        //tempPrefs_Access_Code_Dental = myPreference.getString(
        //         ACCESS_CODE_PREFERENCE_NAME_DENTAL, "");

        // if (!tempPrefs_Access_Code_Medical.equals("")) {
        //if (!TextUtils.isEmpty(tempPrefs_Access_Code_Medical)) {
        //    access_code_edit_text_medical
        //            .setText(tempPrefs_Access_Code_Medical);
        //}

        //if (!TextUtils.isEmpty(tempPrefs_Access_Code_Dental)) {
        //    access_code_edit_text_dental.setText(tempPrefs_Access_Code_Dental);
        //}

        // TODO

        if (sdk_version >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            boolean languageSet = LocaleManager.getSavedLocale(this).getLanguage().equals(Locale.CHINESE.toString());
            if (languageSet)
                access_code_language_en.startAnimation(CustomRadioButton.alpha_to_half);
            else
                access_code_language_zh.startAnimation(CustomRadioButton.alpha_to_half);

            setLanguage(languageSet);
        }

        if(!(Hawk.get(GlobalConstants.useDoctorSignTemplateStatusKey) == null)) {
            useDoctorSignTemplate = Hawk.get(GlobalConstants.useDoctorSignTemplateStatusKey);
        }else{
            useDoctorSignTemplate = false;
        }

        setShowTutorial(useDoctorSignTemplate);



        //access_code_download_update_btn.performClick();

        Log.i(TAG, "show_tutorial_boolean value : " + useDoctorSignTemplate);
        Log.i(TAG, "SettingActivity onResume()");
    }

    private void setLanguage(boolean isChinese) {
        // Intent intent = new Intent(SWITCH_LANGUAGE);
        Locale local;

        if (isChinese) {
            local = Locale.CHINESE;
        } else {
            local = Locale.ENGLISH;
        }

        Context context = LocaleManager.setNewLocale(this, local);
        resources = context.getResources();
        refreshUI(isChinese);
    }

    private void setShowTutorial(boolean isChinese) {
        if (isChinese) {
            rg_doctorapp_usedoctorsignaturetemplate
                    .setBackgroundResource(R.drawable.language_zh_btn);
            rb_doctorapp_usedoctorsignaturetemplate_yes.startAnimation(CustomRadioButton.alpha_to_normal);
            rb_doctorapp_usedoctorsignaturetemplate_no.startAnimation(CustomRadioButton.alpha_to_half);

        }else{
            rg_doctorapp_usedoctorsignaturetemplate
                    .setBackgroundResource(R.drawable.language_en_btn);
            rb_doctorapp_usedoctorsignaturetemplate_yes.startAnimation(CustomRadioButton.alpha_to_half);
            rb_doctorapp_usedoctorsignaturetemplate_no.startAnimation(CustomRadioButton.alpha_to_normal);
        }
    }


    private void refreshUI(boolean isChinese) {
        // /////////////// refreshLogo(boolean isChecked)
        // access_code_language_zh.setChecked(isChinese);
        if (isChinese) {
            access_code_language_zh.performClick();
            //    access_code_btn.setGravity(Gravity.CENTER);
        } else {
            access_code_language_en.performClick();
            //    access_code_btn.setGravity(Gravity.CENTER | Gravity.TOP);
        }

        try {
            tv_language.setText(R.string.language_switch_text);
            tv_use_doctorsignature_template.setText(R.string.doctorapp_doctor_use_doctorsignature_template);
            b_setting_logout_real.setText(R.string.membercard_setting_logout_real);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        rb_doctorapp_usedoctorsignaturetemplate_yes.setText(R.string.doctorapp_doctor_use_doctorsignature_yes);
        rb_doctorapp_usedoctorsignaturetemplate_no.setText(R.string.doctorapp_doctor_use_doctorsignature_no);

        if (isChinese) {
            access_code_language_group
                    .setBackgroundResource(R.drawable.language_zh_btn);
        } else {
            access_code_language_group
                    .setBackgroundResource(R.drawable.language_en_btn);
        }

        ump_version_num.setText(R.string.ump_version_num);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }


}
