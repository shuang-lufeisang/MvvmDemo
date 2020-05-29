package com.duan.mvvmdemo.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.duan.mvvmdemo.R;
import com.duan.mvvmdemo.base.BaseFragment;
import com.duan.mvvmdemo.databinding.FragmentOrderBinding;

/**
 * <pre>
 * author : Duan
 * time : 2020/05/22
 * desc :   订单
 * version: 1.0.0
 * </pre>
 */
public class OrderFragment extends BaseFragment {

    FragmentOrderBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        mBinding = FragmentOrderBinding.bind(view);
        return view;
    }
}
