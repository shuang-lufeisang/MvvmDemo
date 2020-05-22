package com.duan.myactivityfragmentdemo.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.duan.myactivityfragmentdemo.R;
import com.duan.myactivityfragmentdemo.base.BaseFragment;
import com.duan.myactivityfragmentdemo.databinding.FragmentHomeBinding;

/**
 * <pre>
 * author : Duan
 * time : 2020/05/22
 * desc :
 * version: 1.0.0
 * </pre>
 */
public class HomeFragment extends BaseFragment {

    FragmentHomeBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
