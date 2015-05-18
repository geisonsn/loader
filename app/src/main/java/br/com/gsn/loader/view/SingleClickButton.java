package br.com.gsn.loader.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by p001234 on 18/05/15.
 */
public class SingleClickButton extends Button {

    public SingleClickButton(Context context) {
        super(context);
    }

    public SingleClickButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SingleClickButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SingleClickButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean performClick() {
        return isEnabled() && super.performClick();
    }
}
