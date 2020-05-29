package com.duan.mvvmdemo.ui;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.duan.mvvmdemo.R;
import com.duan.mvvmdemo.base.BaseActivity;
import com.duan.mvvmdemo.bridge.request.MusicRequestViewModel;
import com.duan.mvvmdemo.bridge.state.MainActivityViewModel;
import com.duan.mvvmdemo.data.bean.TestAlbum;
import com.duan.mvvmdemo.databinding.ActivitySlidingTabLayoutBinding;
import com.duan.mvvmdemo.databinding.AdapterPlayItemBinding;
import com.duan.mvvmdemo.player.PlayerManager;
import com.kunminx.architecture.ui.adapter.SimpleBaseBindingAdapter;


public class SlidingTabLayoutActivity extends BaseActivity {

    ActivitySlidingTabLayoutBinding mBinding;
    private MainActivityViewModel mMainActivityViewModel;
    private MusicRequestViewModel mMusicRequestViewModel;


    private SimpleBaseBindingAdapter<TestAlbum.TestMusic, AdapterPlayItemBinding> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainActivityViewModel = getActivityViewModelProvider(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.initState();

        mMusicRequestViewModel = getActivityViewModelProvider(this).get(MusicRequestViewModel.class);

        //setContentView(R.layout.activity_sliding_tab_layout);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sliding_tab_layout);
        mBinding.setLifecycleOwner(this);
        mBinding.setVm(mMainActivityViewModel);

        mSharedViewModel.activityCanBeClosedDirectly.observe(this, aBoolean -> {
            //NavController navController = Navigation.findNavController(this, R.id.main)
        });

        mMainActivityViewModel.initTabAndPage.set(true);
        mMainActivityViewModel.pageAssetPath.set("summary.html");


        mAdapter = new SimpleBaseBindingAdapter<TestAlbum.TestMusic, AdapterPlayItemBinding>(SlidingTabLayoutActivity.this, R.layout.adapter_play_item) {
            @SuppressLint("ResourceAsColor")
            @Override
            protected void onSimpleBindItem(AdapterPlayItemBinding binding, TestAlbum.TestMusic item, RecyclerView.ViewHolder holder) {
                // onBindViewHolder
                binding.tvTitle.setText(item.getTitle());
                binding.tvArtist.setText(item.getArtist().getName());
                Glide.with(binding.ivCover.getContext()).load(item.getCoverImg()).into(binding.ivCover);

                int currentIndex = PlayerManager.getInstance().getAlbumIndex();  // 当前正在播放的歌曲的位置

                // 当前正在播放的标识 - audio_track 音轨
                binding.ivPlayStatus.setImageResource(currentIndex == holder.getAdapterPosition() ?
                        R.drawable.ic_audiotrack_black_24dp : android.R.color.transparent);  // R.drawable.ic_audiotrack_transparent_24dp


                // item 点击事件
                binding.getRoot().setOnClickListener(v -> {
                    showShortToast(item.getTitle());      // Toast 歌曲文件名称
                    PlayerManager.getInstance().playAudio(holder.getAdapterPosition());  // 播放所选音乐
                });


            }
        };

        mBinding.rv.setAdapter(mAdapter);

        PlayerManager.getInstance().getChangeMusicLiveData().observe(this, changeMusic -> {

            // TODO tip 1：所有播放状态的改变，都要通过这个 作为 唯一可信源 的 PlayerManager 来统一分发，

            // 如此才能方便 追溯事件源，以及 避免 不可预期的 推送和错误。
            // 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/0168753249

            mAdapter.notifyDataSetChanged();
        });

        mMusicRequestViewModel.getFreeMusicsLiveData().observe(this, musicAlbum -> {
            if (musicAlbum != null && musicAlbum.getMusics() != null){
                mAdapter.setList(musicAlbum.getMusics());
                mAdapter.notifyDataSetChanged();

                // TODO: tip 4: 未做 UnPeek 处理的 用于 request 的 LiveData，在视图控制器重建时会自欧东倒灌数据

                // 一定要记住这一点，因为如果没有妥善处理，这里就会出现预期外的错误，一定要记得它在重建时 是一定会倒灌的。
                // 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/0129483567

                if (PlayerManager.getInstance().getAlbum() == null ||
                        !PlayerManager.getInstance().getAlbum().getAlbumId().equals(musicAlbum.getAlbumMid())){
                    PlayerManager.getInstance().loadAlbum(musicAlbum);
                }
            }
        });

        if (PlayerManager.getInstance().getAlbum() == null) {
            mMusicRequestViewModel.requestFreeMusics();
        } else {
            mAdapter.setList(PlayerManager.getInstance().getAlbum().getMusics());
            mAdapter.notifyDataSetChanged();
        }

    }

    public class ClickProxy{

        // TODO: 2020/5/26 do something 

    }


}
