package com.duan.mvvmdemo.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duan.mvvmdemo.R;
import com.duan.mvvmdemo.base.BaseActivity;
import com.duan.mvvmdemo.base.BaseFragment;
import com.duan.mvvmdemo.bridge.state.MainActivityViewModel;
import com.duan.mvvmdemo.databinding.ActivitySlidingScaleTabLayoutBinding;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class SlidingScaleTabLayoutActivity extends BaseActivity {

    private ActivitySlidingScaleTabLayoutBinding mBinding;

    private MainActivityViewModel mMainActivityViewModel;

    private String[] titles = {"首页", "话题", "私信", "我的"};
    private List<BaseFragment> fragments = new ArrayList<>(); //  fragments


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainActivityViewModel = getActivityViewModelProvider(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.initState();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sliding_scale_tab_layout);
        //setContentView(R.layout.activity_sliding_scale_tab_layout);

        mBinding.setLifecycleOwner(this);
        mBinding.setVm(mMainActivityViewModel);

        // TODO: 2020/5/26 ViewModel中初始化
        //mMainActivityViewModel.initTabAndPage.set(true);

        mBinding.viewPager.setAdapter(new MyPagerAdapter());
        mBinding.slidingScaleTabLayout.setViewPager(mBinding.viewPager);
        mBinding.viewPager.setCurrentItem(3);
        mBinding.viewPager.setOffscreenPageLimit(4);


    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            View view = (View) object;
            return (int) view.getTag();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            TextView textView = new TextView(SlidingScaleTabLayoutActivity.this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            textView.setGravity(Gravity.CENTER);
            textView.setText(titles[position]);
            textView.setTextSize(36);
            textView.setTag(position);
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

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

    class VpFragmentStateAdapter extends FragmentStateAdapter {
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
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }


}
