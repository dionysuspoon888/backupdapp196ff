package ump.doctorapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ump.doctorapp.R;
import ump.doctorapp.model.Voucher1Data;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class VoucherList1Adapter extends RecyclerView.Adapter<VoucherList1Adapter.ViewHolder>{

    private ArrayList<Voucher1Data> list;
    private Context ctx;
    private OnItemClickListener listener;


    public VoucherList1Adapter(Context context, ArrayList<Voucher1Data> lists) {
        ctx = context;
        list = lists;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Create ViewHolder when not enough
        View v = LayoutInflater.from(ctx).inflate(R.layout.voucherlist1_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Define the data shows in the card View
        Voucher1Data currentItem = list.get(position);


        String voucherNumber = currentItem.getVoucherNumber();
        String paidAmount = currentItem.getPaidAmount();

        holder.vocherNumber.setText(voucherNumber);
        holder.paidAmount.setText(paidAmount);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        //ViewHolder set up(mandatory for RecyclerView)
        public ImageView statusImage;
        public TextView vocherNumber;
        public TextView paidAmount;
        public ImageView detailedArrow;



        public ViewHolder(View itemView) {
            super(itemView);
            statusImage= itemView.findViewById(R.id.status_image_view);
            vocherNumber= itemView.findViewById(R.id.reference_number_value_text_view);
            paidAmount = itemView.findViewById(R.id.paid_amount_text_view);
            detailedArrow = itemView.findViewById(R.id.check_voucher1_detail_arrow);





            detailedArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }


    //OnClick UI

    public void setOnItemClickListener(OnItemClickListener listeners) {
        listener = listeners;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

    }



}
