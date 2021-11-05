package com.xue.qin.mygallery.activity;

import android.app.Activity;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.xue.qin.mygallery.R;
import com.xue.qin.mygallery.adapter.MyPagerAdapter;

import java.util.ArrayList;

/**
 * Created by xue.qin on 2018/4/21.
 */

public class PagerActivity extends Activity {
    private ArrayList<String> mData = new ArrayList<>();
    private ViewPager mViewPager;
    private MyPagerAdapter mAdapter;
    private int mCurrent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_activity_layout);
        mData = getIntent().getStringArrayListExtra(GridActivity.DATA);
        mCurrent = getIntent().getIntExtra(GridActivity.CURRENT,0);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mAdapter = new MyPagerAdapter(this,mData);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mCurrent);
    }
}
