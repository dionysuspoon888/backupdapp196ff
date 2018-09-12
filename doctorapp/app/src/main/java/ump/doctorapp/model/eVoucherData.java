package ump.doctorapp.model;

import android.graphics.Bitmap;

/**
 * Created by Dionysus.Poon on 11/6/2018.
 */

public class eVoucherData {
    private Bitmap patient_signature;
    private Bitmap doctor_signature;
    public eVoucherData(Bitmap patient_signature,Bitmap doctor_signature){
        this.patient_signature = patient_signature;
        this.doctor_signature = doctor_signature;

    }

    public Bitmap getPatient_signature() {
        return patient_signature;
    }

    public void setPatient_signature(Bitmap patient_signature) {
        this.patient_signature = patient_signature;
    }

    public Bitmap getDoctor_signature() {
        return doctor_signature;
    }

    public void setDoctor_signature(Bitmap doctor_signature) {
        this.doctor_signature = doctor_signature;
    }
}
