package ump.doctorapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ump.doctorapp.R;
import ump.doctorapp.model.UploadPhotoData;
import ump.doctorapp.model.Voucher1Data;

/**
 * Created by Dionysus.Poon on 13/6/2018.
 */

public class UploadPhotoHistoryAdapter extends RecyclerView.Adapter<UploadPhotoHistoryAdapter.ViewHolder>{

    private ArrayList<UploadPhotoData> list;
    private Context ctx;
    private OnItemClickListener listener;


    public UploadPhotoHistoryAdapter(Context ctx, ArrayList<UploadPhotoData> list) {
        this.ctx = ctx;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Create ViewHolder when not enough
        View v = LayoutInflater.from(ctx).inflate(R.layout.photohistory_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Define the data shows in the card View
        UploadPhotoData currentItem = list.get(position);


        String photoName = currentItem.getPhotoName();
        String uploadDate = currentItem.getUploadDate();
        String remark = currentItem.getRemark();
        Bitmap photoSmall = currentItem.getPhotoBitmap();

        holder.photoSmall.setImageBitmap(photoSmall);
        holder.photoName.setText(photoName);
        holder.uploadDate.setText(uploadDate);
        holder.remark.setText(remark);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        //ViewHolder set up(mandatory for RecyclerView)

        public TextView photoName;
        public TextView uploadDate;
        public TextView remark;
        public ImageView photoSmall;
        public ImageView detailedArrow;
        public RelativeLayout rl_doctorapp_photohistory_container;




        public ViewHolder(View itemView) {
            super(itemView);

            photoName= itemView.findViewById(R.id.tv_doctorapp_photo_name);
            uploadDate = itemView.findViewById(R.id.tv_doctorapp_photo_date);
            remark = itemView.findViewById(R.id.tv_doctorapp_photo_remark);
            photoSmall = itemView.findViewById(R.id.iv_doctorapp_photo_small);
            detailedArrow = itemView.findViewById(R.id.iv_photohistory_detail_arrow);
            rl_doctorapp_photohistory_container = itemView.findViewById(R.id.rl_doctorapp_photohistory_container);






             try {
                 rl_doctorapp_photohistory_container.setOnClickListener(new View.OnClickListener() {
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
             }catch (NullPointerException e){e.printStackTrace();}


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

