package ump.doctorapp.util;


import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.widget.RadioButton;

/**
 * Created by Dionysus.Poon on 11/6/2018.
 */

public class CustomRadioButton extends android.support.v7.widget.AppCompatRadioButton {

//	private int sdk_version;

    private final float font_alpha_half = 0.5f;
    private final float font_alpha_normal = 1.0f;

    public static AlphaAnimation alpha_to_half = null;
    public static AlphaAnimation alpha_to_normal = null;

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);

//		sdk_version = android.os.Build.VERSION.SDK_INT;

        alpha_to_half = new AlphaAnimation(font_alpha_normal, font_alpha_half);

        alpha_to_half.setFillBefore(false);
        alpha_to_half.setFillAfter(true);
        alpha_to_half.setFillEnabled(true);

        alpha_to_normal = new AlphaAnimation(font_alpha_half, font_alpha_normal);
        alpha_to_half.setFillBefore(false);
        alpha_to_normal.setFillAfter(true);
        alpha_to_normal.setFillEnabled(true);

        this.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                CustomRadioButton.this.startAnimation(alpha_to_normal);
            } else {
                CustomRadioButton.this.startAnimation(alpha_to_half);
            }

        });
    }

    // @Override
    // public void setChecked(boolean checked) {
    // if (checked) {
    // CustomRadioButton.this.startAnimation(alpha_to_normal);
    // } else {
    // CustomRadioButton.this.startAnimation(alpha_to_half);
    // }
    //
    // super.setChecked(checked);
    // }

}
