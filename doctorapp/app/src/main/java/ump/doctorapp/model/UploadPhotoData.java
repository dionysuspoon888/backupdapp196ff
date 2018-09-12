package ump.doctorapp.model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Dionysus.Poon on 13/6/2018.
 */

public class UploadPhotoData {
    private Bitmap photoBitmap;
    private String photoName;
    private String remark;
    private String uploadDate;

    public UploadPhotoData(Bitmap photoBitmap,String photoName,String remark,String uploadDate){
        this.photoBitmap = photoBitmap;
        this.photoName = photoName;
        this.remark = remark;
        this.uploadDate = uploadDate;
    }

    public Bitmap getPhotoBitmap() {
        return photoBitmap;
    }

    public void setPhotoBitmap(Bitmap photoBitmap) {
        this.photoBitmap = photoBitmap;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}
