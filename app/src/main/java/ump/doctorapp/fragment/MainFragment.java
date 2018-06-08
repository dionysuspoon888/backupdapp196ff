package ump.doctorapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ump.doctorapp.R;
import ump.doctorapp.VoucherActivity;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class MainFragment  extends BaseFragment{

    Button b_doctorapp_main_checkvoucher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doctorapp_main,container,false);
        b_doctorapp_main_checkvoucher = v.findViewById(R.id.b_doctorapp_main_checkvoucher);
        b_doctorapp_main_checkvoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), VoucherActivity.class));

            }
        });

        return v;
    }
}
