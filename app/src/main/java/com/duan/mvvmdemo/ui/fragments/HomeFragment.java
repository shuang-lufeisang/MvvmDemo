package com.duan.mvvmdemo.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.duan.mvvmdemo.R;
import com.duan.mvvmdemo.base.BaseFragment;
import com.duan.mvvmdemo.databinding.FragmentHomeBinding;

/**
 * <pre>
 * author : Duan
 * time : 2020/05/22
 * desc :  首页
 * version: 1.0.0
 * </pre>
 */
public class HomeFragment extends BaseFragment {

    FragmentHomeBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 2020/5/25 初始化 ViewModel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mBinding = FragmentHomeBinding.bind(view);
//        mBinding.setClick(new ClickProxy());
//        mBinding.setVm(mMainViewModel);
        return view;
    }

}
