package com.duan.mvvmdemo.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * <pre>
 * author : Duan
 * time : 2018/09/18
 * desc :  不可滑动的 ViewPager
 * version: 1.0
 * </pre>
 */
public class NoSlidingViewPager extends ViewPager {

    public NoSlidingViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //return super.onTouchEvent(ev);
        //去掉ViewPager默认的滑动效果， 不消费事件
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
       // return super.onInterceptTouchEvent(ev);
        //不让拦截事件
        return false;
    }
}
