package com.bobo.splayer.modules.mycenter.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.widget.Button;
import android.widget.Toast;

import com.bobo.splayer.R;
import com.bobo.splayer.util.LogUtil;

/**
 * BackButton
 *
 * @author zhoulei create at 2015/10/22 19:01
 */
public class TopBarButton extends Button {
    /**
     * 绘制在Button中的bitmap
     */
    private Bitmap mBitmap;
    /**
     * 要绘制Bitmap相对父布局的（x,y）坐标,mPosX, mPosY=2 表示相对父布局居中
     */
    private float mPosX = 2;
    private float mPosY = 2;

    private boolean isDrawableEnable = false;

    public TopBarButton(Context context) {
        super(context);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        if (isDrawableEnable && mBitmap != null) {
            float x = (this.getMeasuredWidth() - mBitmap.getWidth()) / mPosX;
            float y = (this.getMeasuredHeight() - mBitmap.getHeight()) / mPosY;
            canvas.drawBitmap(mBitmap, x, y, null);
        }
        super.onDraw(canvas);
    }

    /**
     * 设置要绘制在Button中的bitmap
     * @param resId
     */
    public void setBitmap(int resId) {
        this.mBitmap =  BitmapFactory.decodeResource(getResources(), resId);
    }

    /**
     * 设置 bitmap相对它的父布局的相对位置.
     * 比如x = 2表示 bitmap位于x方向的1/2位置，即居中
     *     y = 3表示 bitmap位于y方向的1/3位置
     * @param x
     * @param y
     */
    public void setDrawableRelativePosition(float x, float y) {
        mPosX = x;
        mPosY = y;
    }


    public void setDrawableEnable(boolean isEnable) {
        this.isDrawableEnable = isEnable;
    }
}
