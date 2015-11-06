package com.bobo.splayer.modules.mycenter.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;import java.lang.Override;

/**
 * RoundImage
 * 绘制圆形图片
 * @author zhoulei create at 2015/10/29 16:35
 */
public class RoundImage extends Drawable {
    private final Bitmap mBitmap;
    private final Paint mPaint;
    private final RectF mRectF;
    private final int mBitmapWidth;
    private final int mBitmapHeight;

    public RoundImage(Bitmap bitmap) {
        mBitmap = bitmap;
        mRectF = new RectF();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);      //是否抗锯齿
        mPaint.setDither(true);         //防抖动？
        final BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawOval(mRectF, mPaint);

    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mRectF.set(bounds);
    }
    @Override
    public void setAlpha(int i) {
        if (mPaint.getAlpha() != i) {
            mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
    @Override
    public int getIntrinsicWidth() {
        return mBitmapWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmapHeight;
    }

    public void setAntiAlias(boolean alias) {
        mPaint.setAntiAlias(alias);
        invalidateSelf();
    }

    @Override
    public void setFilterBitmap(boolean isFilter) {
        mPaint.setFilterBitmap(isFilter);
        invalidateSelf();
    }

    @Override
    public void setDither(boolean dither) {
        mPaint.setDither(dither);
        invalidateSelf();
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }
}
