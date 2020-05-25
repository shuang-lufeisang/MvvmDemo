package com.duan.MvvmDemo.base;

import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.duan.MvvmDemo.App;
import com.duan.MvvmDemo.bridge.callback.SharedViewModel;
import com.kunminx.architecture.data.manager.NetworkStateManager;
import com.kunminx.architecture.utils.AdaptScreenUtils;
import com.kunminx.architecture.utils.BarUtils;
import com.kunminx.architecture.utils.ScreenUtils;

/**
 * <pre>
 * author : Duan
 * time : 2020/05/21
 * desc :
 * version: 1.0.0
 * </pre>
 */
public class BaseActivity extends AppCompatActivity {

    protected SharedViewModel mSharedViewModel;
    private ViewModelProvider mActivityProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
       // BarUtils.setStatusBarLightMode(this, true);

        mSharedViewModel = getAppViewModelProvider().get(SharedViewModel.class);

        getLifecycle().addObserver(NetworkStateManager.getInstance());
    }

    public boolean isDebug() {
        return getApplicationContext().getApplicationInfo() != null &&
                (getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    @Override
    public Resources getResources() {
        if (ScreenUtils.isPortrait()) {
            return AdaptScreenUtils.adaptWidth(super.getResources(), 360);
        } else {
            return AdaptScreenUtils.adaptHeight(super.getResources(), 640);
        }
    }

    public void showLongToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    protected ViewModelProvider getAppViewModelProvider() {
        return ((App) getApplicationContext()).getAppViewModelProvider(this);
    }

    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(activity);
        }
        return mActivityProvider;
    }


}
