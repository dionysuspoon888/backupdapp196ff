package ump.doctorapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ump.doctorapp.R;
import ump.doctorapp.UploadPhotoActivity;
import ump.doctorapp.UploadPhotoHistoryActivity;

/**
 * Created by Dionysus.Poon on 12/6/2018.
 */

public class UploadDocumentPhotoFragment extends BaseFragment {
    Button b_doctorapp_upload_uploaddocumentphoto;
    Button b_doctorapp_upload_uploadphotohistory;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doctorapp_uploaddocumentphoto,container,false);
        b_doctorapp_upload_uploaddocumentphoto = v.findViewById(R.id.b_doctorapp_upload_uploaddocumentphoto);
        b_doctorapp_upload_uploadphotohistory = v.findViewById(R.id.b_doctorapp_upload_uploadphotohistory);

        b_doctorapp_upload_uploaddocumentphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UploadPhotoActivity.class));

            }
        });

        b_doctorapp_upload_uploadphotohistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UploadPhotoHistoryActivity.class));

            }
        });


        return v;
    }
}
