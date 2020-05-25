package com.duan.mvvmdemo.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.duan.mvvmdemo.App;
import com.duan.mvvmdemo.bridge.callback.SharedViewModel;
import com.kunminx.architecture.data.manager.NetState;
import com.kunminx.architecture.data.manager.NetworkStateManager;

/**
 * <pre>
 * author : Duan
 * time : 2020/05/21
 * desc :
 * version: 1.0.0
 * </pre>
 */
public class BaseFragment extends Fragment {

    private static final Handler HANDLER = new Handler();
    protected AppCompatActivity mActivity;
    protected SharedViewModel mSharedViewModel;
    protected boolean mAnimationEnterLoaded;
    protected boolean mAnimationLoaded;
    protected boolean mInitDataCame;
    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedViewModel = getAppViewModelProvider().get(SharedViewModel.class);

        //TODO 注意 liveData 的 lambda 回调中不可为空，不然会出现 Cannot add the same observer with different lifecycles 的现象，
        // 详见：https://stackoverflow.com/questions/47025233/android-lifecycle-library-cannot-add-the-same-observer-with-different-lifecycle
        NetworkStateManager.getInstance().mNetworkStateCallback.observe(this, this::onNetworkStateChanged);
    }



    @SuppressWarnings("EmptyMethod")
    protected void onNetworkStateChanged(NetState netState) {
        //TODO 子类可以重写该方法，统一的网络状态通知和处理
    }

    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        HANDLER.postDelayed(() -> {
            mAnimationLoaded = true;
            if (mInitDataCame && !mAnimationEnterLoaded) {
                mAnimationEnterLoaded = true;
                loadInitData();
            }
        }, 280);
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    public void loadInitData() {

    }

    public boolean isDebug() {
        return mActivity.getApplicationContext().getApplicationInfo() != null &&
                (mActivity.getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    public void showLongToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(int stringRes) {
        showLongToast(mActivity.getApplicationContext().getString(stringRes));
    }

    public void showShortToast(int stringRes) {
        showShortToast(mActivity.getApplicationContext().getString(stringRes));
    }

    protected ViewModelProvider getAppViewModelProvider() {
        return ((App) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity);
    }

    protected ViewModelProvider getFragmentViewModelProvider(Fragment fragment) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(fragment);
        }
        return mFragmentProvider;
    }

    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(activity);
        }
        return mActivityProvider;
    }

    public SharedViewModel getSharedViewModel() {
        return mSharedViewModel;
    }

    protected NavController nav() {
        return NavHostFragment.findNavController(this);
    }


}
