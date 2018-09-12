package ump.doctorapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ump.doctorapp.BaseActivity;
import ump.doctorapp.R;
import ump.doctorapp.model.GlobalConstants;

/**
 * Created by Dionysus.Poon on 13/6/2018.
 */

public class UploadPhotoDetailFragment extends BaseFragment {
    public ImageView iv_doctorapp_photodetail;
    public TextView tv_doctorapp_photodetail_name;
    public TextView tv_doctorapp_photodetail_date;
    public TextView tv_doctorapp_photodetail_remark;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doctorapp_uploadphotodetail,container,false);
        iv_doctorapp_photodetail = v.findViewById(R.id.iv_doctorapp_photodetail);
        tv_doctorapp_photodetail_name = v.findViewById(R.id.tv_doctorapp_photodetail_name);
        tv_doctorapp_photodetail_date = v.findViewById(R.id.tv_doctorapp_photodetail_date);
        tv_doctorapp_photodetail_remark = v.findViewById(R.id.tv_doctorapp_photodetail_remark);

        iv_doctorapp_photodetail.setImageBitmap(GlobalConstants.UploadPhotoDataList.get(GlobalConstants.PhotoHistoryClickedPosition).getPhotoBitmap());
        tv_doctorapp_photodetail_name.setText(GlobalConstants.UploadPhotoDataList.get(GlobalConstants.PhotoHistoryClickedPosition).getPhotoName());
        tv_doctorapp_photodetail_date.setText(GlobalConstants.UploadPhotoDataList.get(GlobalConstants.PhotoHistoryClickedPosition).getUploadDate());
        tv_doctorapp_photodetail_remark.setText(GlobalConstants.UploadPhotoDataList.get(GlobalConstants.PhotoHistoryClickedPosition).getRemark());

        return v;
    }
}
