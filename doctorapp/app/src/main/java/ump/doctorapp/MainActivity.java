package ump.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Locale;

import ump.doctorapp.fragment.MainFragment;
import ump.doctorapp.model.GlobalConstants;
import ump.doctorapp.util.LocaleManager;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startFrag(R.id.doctorapp_container,new MainFragment(),getFragmentManager());

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("5699888","G.Lang is "+GlobalConstants.Lang);
        Log.i("5699888","Sys.Lang is "+getResources().getConfiguration().locale.getLanguage().toString());
        if(GlobalConstants.Lang.equals("en")){
            LocaleManager.setNewLocale(this, Locale.ENGLISH);
        }else if(GlobalConstants.Lang.equals("zh")){
            LocaleManager.setNewLocale(this, Locale.CHINESE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
