package ump.doctorapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ump.doctorapp.R;
import ump.doctorapp.UploadPhotoDetailActivity;
import ump.doctorapp.adapter.UploadPhotoHistoryAdapter;
import ump.doctorapp.adapter.VoucherList1Adapter;
import ump.doctorapp.model.GlobalConstants;

/**
 * Created by Dionysus.Poon on 13/6/2018.
 */

public class UploadPhotoHistoryFragment extends BaseFragment implements UploadPhotoHistoryAdapter.OnItemClickListener{


    public RecyclerView  recyclerView;
    public UploadPhotoHistoryAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doctorapp_uploadphotohistory,container,false);

        //RecyclerView setting
        recyclerView = v.findViewById(R.id.rv_uploadphoto_main_contents);
        //better performance
        recyclerView.setHasFixedSize(true);
        //grid view

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new UploadPhotoHistoryAdapter(getActivity(), GlobalConstants.UploadPhotoDataList);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this); //must set listener everytime to create new adapeter(= refresh)

        return v;
    }

    @Override
    public void onItemClick(int position) {

        GlobalConstants.PhotoHistoryClickedPosition = position;
        startActivity(new Intent(getActivity(),UploadPhotoDetailActivity.class));


    }
}
