package ump.doctorapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ump.doctorapp.DoctorSignTemplateActivity;
import ump.doctorapp.LoginActivity;
import ump.doctorapp.R;
import ump.doctorapp.SettingActivity;
import ump.doctorapp.UploadDocumentPhotoActivity;
import ump.doctorapp.VoucherActivity;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class MainFragment  extends BaseFragment{

    Button b_doctorapp_main_uploadphoto;
    Button b_doctorapp_main_checkvoucher;
    Button b_doctorapp_main_doctorsigntemplater;
    Button b_doctorapp_main_setting;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doctorapp_main,container,false);
        b_doctorapp_main_uploadphoto = v.findViewById(R.id.b_doctorapp_main_uploadphoto);
        b_doctorapp_main_checkvoucher = v.findViewById(R.id.b_doctorapp_main_checkvoucher);
        b_doctorapp_main_doctorsigntemplater = v.findViewById(R.id.b_doctorapp_main_doctorsigntemplater);
        b_doctorapp_main_setting = v.findViewById(R.id.b_doctorapp_main_setting);

        b_doctorapp_main_uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UploadDocumentPhotoActivity.class));
            }
        });

        b_doctorapp_main_checkvoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), VoucherActivity.class));

            }
        });

        b_doctorapp_main_doctorsigntemplater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DoctorSignTemplateActivity.class) );

            }
        });

        b_doctorapp_main_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
                getActivity().finish();

            }
        });

        return v;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getActivity(),LoginActivity.class));
        getActivity().finish();
    }
}
