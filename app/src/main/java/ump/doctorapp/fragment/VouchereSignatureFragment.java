package ump.doctorapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.orhanobut.hawk.Hawk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import ump.doctorapp.R;
import ump.doctorapp.VoucherActivity;
import ump.doctorapp.model.GlobalConstants;

/**
 * Created by Dionysus.Poon on 14/6/2018.
 */

public class VouchereSignatureFragment extends BaseFragment {
    SignaturePad b_patientsign_template_signature_pad;
    SignaturePad b_doctorsign_template_signature_pad;

    Button b_patientsign_esignature_clear_button;
    Button b_doctorsign_esignature_clear_button;

    boolean patientsignsStatus = false;
    boolean doctorsignsStatus = false;

    Button b_doctorsign_esignature_submit_button;

    ImageView iv_doctorapp__esignature_doctorsigntemplate;
    LinearLayout ll_doctorapp_esignature_clearbutton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doctorapp_esignature,container,false);
        //init
        patientsignsStatus = false;
        doctorsignsStatus = false;

        b_patientsign_template_signature_pad = v.findViewById(R.id.b_patientsign_template_signature_pad);
        b_doctorsign_template_signature_pad = v.findViewById(R.id.b_doctorsign_template_signature_pad);
        b_patientsign_esignature_clear_button = v.findViewById(R.id.b_patientsign_esignature_clear_button);
        b_doctorsign_esignature_clear_button = v.findViewById(R.id.b_doctorsign_esignature_clear_button);
        b_doctorsign_esignature_submit_button = v.findViewById(R.id.b_doctorsign_esignature_submit_button);

        iv_doctorapp__esignature_doctorsigntemplate = v.findViewById(R.id.iv_doctorapp__esignature_doctorsigntemplate);
        ll_doctorapp_esignature_clearbutton = v.findViewById(R.id.ll_doctorapp_esignature_clearbutton);

        if(GlobalConstants.useDoctorSignTemplate){
            doctorsignsStatus = true;
            b_doctorsign_template_signature_pad.setVisibility(View.GONE);
            ll_doctorapp_esignature_clearbutton.setVisibility(View.GONE);
            iv_doctorapp__esignature_doctorsigntemplate.setVisibility(View.VISIBLE);

            if(!(Hawk.get(GlobalConstants.doctorSignTemplateKey) == null)) {
                iv_doctorapp__esignature_doctorsigntemplate.setImageBitmap(GlobalConstants.doctorSignTemplate);
            }

        }else {
            b_doctorsign_template_signature_pad.setVisibility(View.VISIBLE);
            ll_doctorapp_esignature_clearbutton.setVisibility(View.VISIBLE);
            iv_doctorapp__esignature_doctorsigntemplate.setVisibility(View.GONE);

        }

        b_patientsign_template_signature_pad.setOnSignedListener(new SignaturePad.OnSignedListener() {
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

        b_patientsign_esignature_clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_patientsign_template_signature_pad.clear();
            }
        });



        b_doctorsign_template_signature_pad.setOnSignedListener(new SignaturePad.OnSignedListener() {
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

        b_doctorsign_esignature_clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_doctorsign_template_signature_pad.clear();
            }
        });


        b_doctorsign_esignature_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(patientsignsStatus == true && doctorsignsStatus == true) {

                    GlobalConstants.eVoucherPatientSignatureTreeMap = new TreeMap<>();
                    GlobalConstants.eVoucherPatientSignatureTreeMap.put("0",b_patientsign_template_signature_pad.getTransparentSignatureBitmap());

                    GlobalConstants.eVoucherDoctorSignatureTreeMap = new TreeMap<>();
                    if(GlobalConstants.useDoctorSignTemplate){
                        if(!(Hawk.get(GlobalConstants.doctorSignTemplateKey) == null)) {
                            GlobalConstants.eVoucherDoctorSignatureTreeMap.put("0", GlobalConstants.doctorSignTemplate);
                        }
                    }else {
                        GlobalConstants.eVoucherDoctorSignatureTreeMap.put("0", b_doctorsign_template_signature_pad.getTransparentSignatureBitmap());
                    }

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
}
