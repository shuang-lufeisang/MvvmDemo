package com.duan.MvvmDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.duan.MvvmDemo.adapter.SimpleHomeAdapter;
import com.duan.MvvmDemo.base.BaseActivity;
import com.duan.MvvmDemo.ui.BottomNavigationActivity;
import com.duan.MvvmDemo.ui.BottomNavigationStylesActivity;
import com.duan.MvvmDemo.ui.CommonTabActivity;
import com.duan.MvvmDemo.ui.ParallelNestedScrollingActivity;
import com.duan.MvvmDemo.ui.SlidingTabLayoutActivity;

public class MainActivity extends BaseActivity {

    private Context mContext = this;

    private final String[] mItems = {
            "SlidingTabLayout",
            "BottomNavigationView",
            "BottomNavigationStyles",
            "CommonTabLayout",
            "kotlin:ParallelNestedScroll"
    };

    private final Class<?>[] mClasses = {

            SlidingTabLayoutActivity.class,
            BottomNavigationActivity.class,
            BottomNavigationStylesActivity.class,
            CommonTabActivity.class,
            ParallelNestedScrollingActivity.class

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = new ListView(mContext);
        listView.setCacheColorHint(Color.TRANSPARENT);
        listView.setFadingEdgeLength(0);
        listView.setAdapter(new SimpleHomeAdapter(mContext, mItems));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, mClasses[position]);
                startActivity(intent);
            }
        });

        setContentView(listView);
    }
}
