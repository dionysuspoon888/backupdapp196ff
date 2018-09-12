package ump.doctorapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ump.doctorapp.R;
import ump.doctorapp.VouchereSignatureActivity;

/**
 * Created by Dionysus.Poon on 14/6/2018.
 */

public class VoucherDetailMethod2Fragment extends BaseFragment {
     Button b_doctorapp_esignature;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_voucherdetailmethod2,container,false);
        b_doctorapp_esignature = v.findViewById(R.id.b_doctorapp_esignature);
        b_doctorapp_esignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), VouchereSignatureActivity.class));
            }
        });
        return v;
    }


}
