package ump.doctorapp.model;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class Voucher1Data {

    private String voucherNumber;
    private String paidAmount;


    public Voucher1Data(String voucherNumber,String paidAmount){
        this.voucherNumber = voucherNumber;
        this.paidAmount = paidAmount;


    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getPaidAmount() {
        return paidAmount;
    }


}
