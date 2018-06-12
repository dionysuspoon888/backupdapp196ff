package ump.doctorapp.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.orhanobut.hawk.Hawk;

import ump.doctorapp.MainActivity;
import ump.doctorapp.R;
import ump.doctorapp.VoucherActivity;
import ump.doctorapp.model.GlobalConstants;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class LoginFragment extends BaseFragment {

    EditText et_doctorapp_login_cliniccode;
    EditText et_doctorapp_login_doctorcode;
    EditText et_doctorapp_login_password;

    public Button b_doctorapp_login;
    public CheckBox cb_doctorapp_keeplogin;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.fragment_doctorapp_login,container,false);
        et_doctorapp_login_cliniccode = v.findViewById(R.id.et_doctorapp_login_cliniccode);
        et_doctorapp_login_doctorcode = v.findViewById(R.id.et_doctorapp_login_doctorcode);
        et_doctorapp_login_password = v.findViewById(R.id.et_doctorapp_login_password);
        b_doctorapp_login = v.findViewById(R.id.b_doctorapp_login);
        cb_doctorapp_keeplogin = v.findViewById(R.id.cb_doctorapp_keeplogin);


        b_doctorapp_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_doctorapp_login_cliniccode.getText().toString().trim().toUpperCase().equals("CENTRAL01") &&
                  et_doctorapp_login_doctorcode.getText().toString().trim().toUpperCase().equals("G999") &&
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

        rememberLogin();

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        saveLogin();

    }

    //method to remember the account .Store it to local storage by using Hawk Library  when it is back to the login page
    public void rememberLogin(){

         if(!(Hawk.get(GlobalConstants.checkBoxKey) == null)) {
             if (((String) Hawk.get(GlobalConstants.checkBoxKey)).equals("true")) {

                 Log.i("12930","rememberLogin  SS");
                 cb_doctorapp_keeplogin.setChecked(true);

                 if (!(Hawk.get(GlobalConstants.clinicCodeKey) == null)) {
                     String request_email = (String) Hawk.get(GlobalConstants.clinicCodeKey);
                     et_doctorapp_login_cliniccode.setText(request_email);
                 }

                 if (!(Hawk.get(GlobalConstants.doctorCodeKey) == null)) {
                     String request_pass = (String) Hawk.get(GlobalConstants.doctorCodeKey);
                     et_doctorapp_login_doctorcode.setText(request_pass);
                 }

                 if (!(Hawk.get(GlobalConstants.passwordKey) == null)) {
                     String request_pass = (String) Hawk.get(GlobalConstants.passwordKey);
                     et_doctorapp_login_password.setText(request_pass);
                 }

             }else{
                 Log.i("12930","rememberLogin  FF");
             }

         }else{
             Log.i("12930","rememberLogin  Null");
         }




    }

    //method to remember the account&password .Store it to local storage by using Hawk Library
    public void saveLogin(){
        Log.i("12930","saveLogin");
        String cliniccode = et_doctorapp_login_cliniccode.getText().toString().trim();
        String doctorcode = et_doctorapp_login_doctorcode.getText().toString().trim();
        String password = et_doctorapp_login_password.getText().toString().trim();
        if(cb_doctorapp_keeplogin.isChecked()){
            Log.i("12930","saveLogin SS");





            Hawk.put(GlobalConstants.clinicCodeKey,cliniccode);
            Hawk.put(GlobalConstants.doctorCodeKey,doctorcode);
            Hawk.put(GlobalConstants.passwordKey,password);
            Hawk.put(GlobalConstants.checkBoxKey,"true");

        }else{
            Log.i("12930","saveLogin FF");

                Hawk.delete(GlobalConstants.clinicCodeKey);
                Hawk.delete(GlobalConstants.doctorCodeKey);
                Hawk.delete(GlobalConstants.passwordKey);

            Hawk.put(GlobalConstants.checkBoxKey,"false");
        }

    }



}
