package ump.doctorapp.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ump.doctorapp.MainActivity;
import ump.doctorapp.R;
import ump.doctorapp.VoucherActivity;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class LoginFragment extends BaseFragment {

    EditText et_doctorapp_login_cliniccode;
    EditText et_doctorapp_login_doctorcode;
    EditText et_doctorapp_login_password;

    Button b_doctorapp_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doctorapp_login,container,false);
        et_doctorapp_login_cliniccode = v.findViewById(R.id.et_doctorapp_login_cliniccode);
        et_doctorapp_login_doctorcode = v.findViewById(R.id.et_doctorapp_login_doctorcode);
        et_doctorapp_login_password = v.findViewById(R.id.et_doctorapp_login_password);
        b_doctorapp_login = v.findViewById(R.id.b_doctorapp_login);

        b_doctorapp_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_doctorapp_login_cliniccode.getText().toString().trim().equals("Central01") &&
                  et_doctorapp_login_doctorcode.getText().toString().trim().equals("G999") &&
                  et_doctorapp_login_password.getText().toString().trim().equals("123456Aa")){
                    startActivity(new Intent(getActivity(), MainActivity.class));


                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(R.string.invalid_user_title);
                    builder.setMessage(R.string.invalid_user_message);
                    builder.setPositiveButton(R.string.disclaimer_ok, null);
                    builder.create().show();
                }
            }
        });
        return v;
    }
}
