package com.duan.mvvmdemo.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.duan.mvvmdemo.R;
import com.duan.mvvmdemo.base.BaseFragment;
import com.duan.mvvmdemo.databinding.ActivitySlidingTableLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class SlidingTabLayoutActivity extends AppCompatActivity {

    ActivitySlidingTableLayoutBinding mBinding;

    private String[] titles = {"首页", "话题", "私信", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sliding_table_layout);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sliding_table_layout);

        mBinding.viewPager.setAdapter(new VpAdapter());
    }


    private List<BaseFragment> fragments = new ArrayList<>(); //  BaseFragment

    class VpAdapter extends RecyclerView.Adapter{
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

    class VpFragmentStateAdapter extends FragmentStateAdapter{
        public VpFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        public VpFragmentStateAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        public VpFragmentStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return null;
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }


}
