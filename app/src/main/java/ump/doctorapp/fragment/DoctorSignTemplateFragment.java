package ump.doctorapp.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.orhanobut.hawk.Hawk;

import java.util.TreeMap;

import ump.doctorapp.MainActivity;
import ump.doctorapp.R;
import ump.doctorapp.VoucherActivity;
import ump.doctorapp.model.GlobalConstants;

/**
 * Created by Dionysus.Poon on 11/6/2018.
 */

public class DoctorSignTemplateFragment extends BaseFragment {
    public SignaturePad b_doctorsign_template_signature_pad;
    public Button b_doctorsign_template_clear_button;
    public Button b_doctorsign_template_submit_button;
    public Boolean doctorsignsStatus;


    //m
    private Bitmap bitmap;

    private Canvas canvas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doctorsigntemplate_fragment,container,false);

        //init
        doctorsignsStatus = false;
        b_doctorsign_template_signature_pad = v.findViewById(R.id.b_doctorsign_template_signature_pad);
        b_doctorsign_template_clear_button = v.findViewById(R.id.b_doctorsign_template_clear_button);
        b_doctorsign_template_submit_button = v.findViewById(R.id.b_doctorsign_template_submit_button);

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

        b_doctorsign_template_clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_doctorsign_template_signature_pad.clear();
            }
        });

        b_doctorsign_template_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(doctorsignsStatus == true) {


                    if(b_doctorsign_template_signature_pad.getSignatureBitmap() != null) {

                        GlobalConstants.doctorSignTemplate = b_doctorsign_template_signature_pad.getTransparentSignatureBitmap();

                        Hawk.put(GlobalConstants.doctorSignTemplateKey, BitmapToBase64(GlobalConstants.doctorSignTemplate));
                    }
                    GlobalConstants.useDoctorSignTemplate = true;

                    Hawk.put(GlobalConstants.useDoctorSignTemplateStatusKey,GlobalConstants.useDoctorSignTemplate );

                    String eSignatureSubmit = "Doctor Signature Template has saved";

                    if (isAdded()) {
                        eSignatureSubmit = getString(R.string.voucher_esignaturetemplatesaved);
                    }

                    Toast.makeText(getActivity().getBaseContext(), eSignatureSubmit, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), MainActivity.class));

                    //m
//                    if (addSignatureToGallery(takeScreenShot(ll_evoucher_scan))) {
//                        Toast.makeText(getActivity(), "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // Toast.makeText(getBaseContext(), "Unable to store the signature", Toast.LENGTH_SHORT).show();
//                    }

//                if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
//                    Toast.makeText(MainActivity.this, "SVG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
//                }

                }else{
                    String tmpeSignaturePleaseSign = "請簽署";
                    if (isAdded()) {
                        tmpeSignaturePleaseSign = getString(R.string.voucher_esignaturepleasesign);
                    }
                    Toast.makeText(getActivity().getBaseContext(), tmpeSignaturePleaseSign, Toast.LENGTH_SHORT).show();
                }
            }
        });




        Log.i(TAG, "onCreateView");
        return v;
    }
}
