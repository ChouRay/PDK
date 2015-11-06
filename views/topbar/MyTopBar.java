package com.bobo.splayer.modules.mycenter.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bobo.splayer.R;

/**
 * MyTopBar 一个自定义的Topbar模版， 它共分为左、中、右三段，左边为带返回图标的按钮，
 * 右边也是一个Button，中间为TextView
 * Created by zhoulei on 2015/10/21.
 */
public class MyTopBar extends RelativeLayout{
    /**
     * 左边的按钮
     */
    private TopBarButton mBtnLeft;
    /**
     * 右边的按钮
     */
    private TopBarButton mBtnRight;
    /**
     * 中间的标题
     */
    private TextView mTvTitle;

    private int mLeftTextColor;

    private Drawable mLeftBackground;
    private String mLeftText;

    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;

    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mToptitle;

    /**
     * 定义三个布局参数
     */
    private LayoutParams mLayoutParams, mRightParams, mTitleParams;

    /**
     * Tobar左右按钮的点击事件接口
     */
    public interface topbarClickListener {
        /**监听左边的点击事件*/
        void leftClick();

        /**
         * 监听右边按钮的点击事件
         */
        void rightClick();
    }

    /**创建接口对象*/
    public topbarClickListener listener;

    /**
     * 创建为事件接口赋值的方法
     */
    public void setOnTopBarClickListener(topbarClickListener listener) {
        this.listener = listener;
    }

    /**
     *  构造方法，初始化成员
     */

    public MyTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        //将XML中定义的自定义属性映射到attrs中。
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);

        //从ta结构中获取数据，类似一种key,value结构，通过R.styleable.Topbar_属性名获取
        mLeftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.Topbar_leftBackground);
        mLeftText = ta.getString(R.styleable.Topbar_leftText);

        mRightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.Topbar_rightBackground);
        mRightText = ta.getString(R.styleable.Topbar_rightText);

        mTitleTextSize = ta.getDimension(R.styleable.Topbar_titleTextSize, 0);
        mTitleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor, 0);
        mToptitle = ta.getString(R.styleable.Topbar_toptitle);

        //进行垃圾回收
        ta.recycle();

        setBackgroundColor(getResources().getColor(R.color.colorPrimary));    //设置View的背景颜色

        //初始化控件
        mBtnLeft = new TopBarButton(context);
        mBtnRight = new TopBarButton(context);
        mTvTitle = new TextView(context);

        //设置控件的值
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBtnLeft.setBackground(mLeftBackground);
            mBtnRight.setBackground(mRightBackground);
        }else {
            mBtnLeft.setBackground(mLeftBackground);
            mBtnRight.setBackground(mRightBackground);
        }

        mBtnRight.setTextColor(mRightTextColor);        //设置文字颜色
        mBtnRight.setText(mRightText);                  //设置文本
        mTvTitle.setTextColor(mTitleTextColor);         //设置字体颜色
        mTvTitle.setTextSize(mTitleTextSize);           //设置字体大小
        mTvTitle.setText(mToptitle);                    //设置文本
        mTvTitle.setGravity(Gravity.CENTER);           //居中显示

        //设置左边布局属性的width和height
        mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //设置对齐方式为父容器的左侧
        mLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        //将左边按钮添加到视图中，并设置布局属性
        addView(mBtnLeft, mLayoutParams);

        //设置右边布局属性的width和height
        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //设置对齐方式为父容器的右侧
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        //将右边按钮添加到视图中，并设置布局属性
        addView(mBtnRight, mRightParams);

        //设置中间布局属性的width和height
        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //设置对齐方式为居中对齐
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        //将中间TextView添加到视图中，并设置布局属性
        addView(mTvTitle, mTitleParams);

        //添加左侧按钮的Click事件
        mBtnLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });

        //添加右侧按钮的Click事件
        mBtnRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }


    /**
     * 设置右边按钮的文字
     * @param text
     */
    public void setRightText(String text) {
        mBtnRight.setText(text);
    }

    /**
     * 设置当前标题Title
     * @param title
     */
    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    /**
     * 设置左边按钮是否隐藏，true隐藏， false消失
     *
     * @param flag
     */
    public void setLeftButtonIsVisiable(boolean flag) {
        if (flag) {
            mBtnLeft.setVisibility(View.VISIBLE);
        } else {
            mBtnLeft.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右边按钮是否隐藏，true隐藏， false消失
     *
     * @param flag
     */
    public void setRightButtonIsVisiable(boolean flag) {
        if (flag) {
            mBtnRight.setVisibility(View.VISIBLE);
        } else {
            mBtnRight.setVisibility(View.GONE);
        }
    }

    /**
     * 设置左边的按钮的图标
     * @param resId  图标的资源id
     */
    public void setLeftButtonDrawable(int resId) {
        mBtnLeft.setBitmap(resId);
    }

    /**
     *设置右边的按钮的图标
     * @param resId 图标资源id
     */
    public void setRightButtonDrawable(int resId) {
        mBtnRight.setBitmap(resId);
    }

    /**
     * 设置 右边Drawable相对它的宿主Button的相对位置.
     * 比如x = 2表示 bitmap位于x方向的1/2位置，即居中.
     *     y = 3表示 bitmap位于y方向的1/3位置.
     * @param x
     * @param y
     */
    public void setRightDrawablePosition(float x, float y) {
        mBtnRight.setDrawableRelativePosition(x, y);
    }
    /**
     * 设置左边 Drawable相对它的宿主Button的相对位置.
     * 比如x = 2表示 bitmap位于x方向的1/2位置，即居中.
     *     y = 3表示 bitmap位于y方向的1/3位置.
     * @param x
     * @param y
     */
    public void setLeftDrawablePosition(float x, float y) {
        mBtnLeft.setDrawableRelativePosition(x, y);
    }

    /**
     * 设置左边Button是否可以带有图标Drawable
     */
    public void setLeftDrawableEnable(boolean isEnable) {
        mBtnLeft.setDrawableEnable(isEnable);
    }

    /**
     * 设置右边边Button是否可以带有图标Drawable
     */
    public void setRightDrawableEnable(boolean isEnable) {
        mBtnRight.setDrawableEnable(isEnable);
    }


}