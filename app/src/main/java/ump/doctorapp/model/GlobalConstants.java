package ump.doctorapp.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class GlobalConstants {

    //For whole application

    //Testing Account
    //ID: test888 PWD: Z88888888
    public static boolean test = false;

    //AutoSizing
    //public static int autoTextSize = 16;
    public static int width = 1440;
    public static int height = 2560;

    //Lang
    // zh = chinese, en = english
    public static String Lang ;
    //Token   Access
    public static String TOKEN = "";

    //Current Location
    public static String Location = "";

    //Hawk Key for Login Saving

    public static final String doctorSignTemplateKey = "DoctorSignTemplateKey";
    public static final String useDoctorSignTemplateStatusKey = "useDoctorSignTemplateStatusKey";
    public static final String useeSignMethodKey = "useeSignMethodKey";
    public static final String clinicCodeKey = "clinicCodeKey";
    public static final String doctorCodeKey = "doctorCodeKey";
    public static final String passwordKey = "passwordKey";
    public static final String checkBoxKey = "checkBoxKey";
    /////
    //For doctorapp eVoucher
    public static TreeMap<String,Bitmap> eVoucherDataTreeMap = new TreeMap<>();
    public static Boolean eSignatureStatus = false;

    public static Boolean useDoctorSignTemplate = false;
    public static Bitmap doctorSignTemplate;

    //2
    public static TreeMap<String,Bitmap> eVoucherPatientSignatureTreeMap = new TreeMap<>();
    public static TreeMap<String,Bitmap> eVoucherDoctorSignatureTreeMap = new TreeMap<>();

    public static Bitmap eVoucherMethod2SnapShot;





    //////////
    //For eSinature Template
    public static Bitmap bitmap ;
    public static String Base64String = "";
    public static byte size = 0;


    //Voucher

    public static ArrayList<Voucher1Data> Voucher1DetailList = new ArrayList<>();

    public static String voucherListpositionClicked = "";

    public static TreeMap<String,String> eSignatureTable = new TreeMap<>();
    public static Hashtable<String,String> signingDateTable = new Hashtable<>();

   //Take Photo
    public static Bitmap photoBitmap;
    public static ArrayList<UploadPhotoData> UploadPhotoDataList = new ArrayList<>();
    public static int PhotoHistoryClickedPosition = 0;
    public static int takePhotoCount = 0;





}
