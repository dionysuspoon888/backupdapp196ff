package ump.doctorapp.fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import ump.doctorapp.R;
import ump.doctorapp.model.GlobalConstants;

/**
 * Created by Dionysus.Poon on 8/6/2018.
 */

public class BaseFragment extends Fragment {

    public  String TAG = this.getClass().getSimpleName();

    ProgressDialog progressDialog;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        GlobalConstants.Location = TAG;

    }

    //start the fragment without back stack (if clicked back, it would not go back to this fragment)
    public static void startFrag(int container, Fragment fragment, FragmentManager fm) {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(container,fragment)
                .commit();
        //

    }

    //start the fragment withback stack (if clicked back, it would go back to this fragment)
    public static void startFragBackState(int container, Fragment fragment, FragmentManager fm) {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(container,fragment)
                .addToBackStack(null)
                .commit();

    }
    public void onBackPressed()
    {
        // Catch back action and pops from backstack
        // (if you called previously to addToBackStack() in your transaction)
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
    }

    //generate QR code(Maybe for later use)
    public Bitmap generateQRCode(String text) {

        Bitmap mBitmap=null;
//        LogUtil.info("generateQRCode", "generateQRCode:" + (TextUtils.isEmpty(qrMbid) || mBitmap == null || !mbid.equals(qrMbid)) + ", " + TextUtils.isEmpty(mbid)
//                + ", " + (mBitmap == null) + ", " + (!mbid.equals(qrMbid)) +", "+fullCardNo);



        QRCodeWriter writer = new QRCodeWriter();
        try {
            Map<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 0); /* default = 4 */
            BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, (int) convertDpToPixel(120, getActivity()), (int) convertDpToPixel(120, getActivity()), hints);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    mBitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return mBitmap;

//     imgQRCode.setImageBitmap(mBitmap);


//        try {
//            // generate a 150x150 QR code
//            Bitmap bm = encodeAsBitmap(barcode_content, BarcodeFormat.QR_CODE, 150, 150);
//
//            if(bm != null) {
//                image_view.setImageBitmap(bm);
//            }
//        } catch (WriterException e) { //eek }
    }


    //generate Bar code(Maybe for later use)
    public Bitmap getBarCode(String data) {
//        Bitmap mBitmap = null;
//        com.google.zxing.Writer c9 = new Code39Writer();

        int width;
        int height;
//        if(Utils.getScreenWidth(mContext) <= 1080){
        width = (int) convertDpToPixel(200, getActivity());
        height = (int) convertDpToPixel(40, getActivity());
        MultiFormatWriter writer = new MultiFormatWriter();
        String finalData = Uri.encode(data);

        // Use 1 as the height of the matrix as this is a 1D Barcode.
        BitMatrix bm = null;
        try {
            Map<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 0); /* default = 4 */
            bm = writer.encode(finalData, BarcodeFormat.CODE_128, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
//            LogUtil.info("getBarCode", "getBarCode:"+e.toString());
        }
        Bitmap imageBitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);

        for (int i = 0; i < bm.getWidth(); i++) {
            // Paint columns of width 1
            int[] column = new int[height];
            Arrays.fill(column, bm.get(i, 0) ? Color.BLACK : Color.TRANSPARENT);
            imageBitmap.setPixels(column, 0, 1, i, 0, 1, height);
        }
        return imageBitmap;
    }



    public static float convertDpToPixel(float dp, Context context) {
        float px = dp * getDensity(context);
        return px;
    }

    public static float convertPixelToDp(float px, Context context) {
        float dp = px / getDensity(context);
        return dp;
    }

    public static float getDensity(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    //For Validation
    public void alertCenterStyle(String alertMessage,Activity activity){
        // String a = getString(R.string.validate_positive_amount_paid_by_other);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.attention_alert_title);

        //builder.setMessage(R.string.validate_positive_amount_paid_by_other);
        builder.setMessage(alertMessage);

        builder.setPositiveButton(R.string.disclaimer_ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();

        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);

        TextView titleView = (TextView)dialog.findViewById(getResources().getIdentifier("alertTitle", "id", "android"));
        if (titleView != null) {
            titleView.setGravity(Gravity.CENTER);
        }
        final Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        //LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
        LinearLayout.LayoutParams positiveButtonLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        positiveButtonLL.gravity = Gravity.CENTER;
        positiveButton.setLayoutParams(positiveButtonLL);

        return;

    }

    //Custom alertDialog with custom message
    public void alertCenterStyle(int alertMessage,Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.attention_alert_title);

        //builder.setMessage(R.string.validate_positive_amount_paid_by_other);
        builder.setMessage(alertMessage);

        builder.setPositiveButton(R.string.disclaimer_ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();

        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);

        TextView titleView = (TextView)dialog.findViewById(getResources().getIdentifier("alertTitle", "id", "android"));
        if (titleView != null) {
            titleView.setGravity(Gravity.CENTER);
        }
        final Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        //LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
        LinearLayout.LayoutParams positiveButtonLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        positiveButtonLL.gravity = Gravity.CENTER;
        positiveButton.setLayoutParams(positiveButtonLL);

        return;

    }




}
