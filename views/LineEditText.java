package com.bobo.splayer.modules.mycenter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.bobo.splayer.R;

/**
 * LineEditText
 *
 * @author zhoulei create at 2015/10/21 15:41
 */
public class MyEditText extends EditText {
    public final static int STATUS_FOCUSED = 1;
    public final static int STATUS_UNFOCUSED = 2;
    public final static int STATUS_ERROR = 3;
    private Paint mPaint;
    private int mColor;
    private int mStatus = STATUS_UNFOCUSED;

    private boolean isClearPassword = false;

    /**
     * 密码EditText框后面的眼睛图标，表示是否明文显示密码
     */
    private Drawable mDrawableEye;
    private Drawable mDrawableEyeDown;

    private Context mContext;
    public MyEditText(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public LineEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        setStatus(mStatus);
        mDrawableEye = mContext.getResources().getDrawable(R.drawable.eye1);
        mDrawableEyeDown = mContext.getResources().getDrawable(R.drawable.eye2);
        setEyeDrawable();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    private void setStatus(int status) {
        this.mStatus = status;

        if (status == STATUS_FOCUSED) {
            //mLeftDrawable = getResources().getDrawable()
        }
        postInvalidate();
    }

    /***
     * 设置 是否明文显示密码的那个Drawable,在此为一个Eye图标
     */
    private void setEyeDrawable() {
        if (isClearPassword == true) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, mDrawableEyeDown, null);
            setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isClearPassword = false;
        }else {
            setCompoundDrawablesWithIntrinsicBounds(null, null,mDrawableEye, null);
            setTransformationMethod(PasswordTransformationMethod.getInstance());
            isClearPassword = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setFocusable(true);
        if (mDrawableEyeDown != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            if (rect.contains(eventX, eventY))
            {
//                setFocusable(true);
//                setFocusableInTouchMode(true);
//                requestFocus();
//                findFocus();
                setEyeDrawable();
            }
        }
        return super.onTouchEvent(event);
    }




}
