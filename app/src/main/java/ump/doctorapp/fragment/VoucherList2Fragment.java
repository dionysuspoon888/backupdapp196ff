package ump.doctorapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ump.doctorapp.R;
import ump.doctorapp.VoucherDetail2Activity;
import ump.doctorapp.adapter.VoucherList2Adapter;
import ump.doctorapp.model.GlobalConstants;
import ump.doctorapp.model.Voucher1Data;


/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class VoucherList2Fragment extends BaseFragment  implements VoucherList2Adapter.OnItemClickListener{
    RecyclerView recyclerView;
    VoucherList2Adapter adapter;
    ArrayList<Voucher1Data> Voucher2List = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_voucher1list,container,false);

        Voucher2List = new ArrayList<>();

        if(GlobalConstants.eSignatureStatus) {
            Voucher2List.add(new Voucher1Data("B317336", "0.0"));
        }



        ListView VoucherListView1 = v.findViewById(R.id.voucher1_list_view);
        VoucherListView1.setAdapter(new VoucherListView2Adapter(getActivity(), Voucher2List));
        VoucherListView1.setOnItemClickListener((adapterView, view, i, l) -> {

            Intent intent = new Intent(getActivity(), VoucherDetail2Activity.class);
            startActivity(intent);


        });


        //RecyclerView setting
        recyclerView = v.findViewById(R.id.rv_voucher1);
        //better performance
        recyclerView.setHasFixedSize(true);
        //grid view

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        adapter = new VoucherList2Adapter(getActivity(), Voucher2List);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this); //must set listener everytime to create new adapeter(= refresh)

        return v;
    }

    @Override
    public void onItemClick(int position) {

    }

    /**
     * Construct the row of the claim list table
     */
    public class VoucherListView2Adapter extends ArrayAdapter<Voucher1Data> {

        public VoucherListView2Adapter(@NonNull Context context, @NonNull List<Voucher1Data> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.voucherlist2_item, parent, false);
            }

            Voucher1Data voucher1 = getItem(position);
            assert voucher1 != null;

            ImageView iv_statusImage= convertView.findViewById(R.id.status_image_view);
            TextView tv_vocherNumber= convertView.findViewById(R.id.reference_number_value_text_view);
            TextView tv_paidAmount = convertView.findViewById(R.id.paid_amount_text_view);
            ImageView iv_detailedArrow = convertView.findViewById(R.id.check_voucher1_detail_arrow);
            TextView esignning_date_text_view = convertView.findViewById(R.id.esignning_date_text_view);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            esignning_date_text_view.setText(dateFormat.format(date));


            //Define the data shows in the card View

            String voucherNumber = voucher1.getVoucherNumber();
            String paidAmount = voucher1.getPaidAmount();

            tv_vocherNumber.setText(voucherNumber);
            tv_paidAmount.setText(paidAmount);

            return convertView;
        }
    }


}
