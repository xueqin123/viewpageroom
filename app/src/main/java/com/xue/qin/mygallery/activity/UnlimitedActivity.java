package com.xue.qin.mygallery.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xue.qin.mygallery.R;
import com.xue.qin.mygallery.adapter.UnlimitedAdapter;
import com.xue.qin.mygallery.interfaces.DataProvider;
import com.xue.qin.mygallery.views.UnlimitedViewPager;

import java.util.ArrayList;

/**
 * Created by xue.qin on 2018/4/21.
 */

public class UnlimitedActivity extends Activity implements DataProvider {
    private UnlimitedViewPager mUnlimitViewPager;
    private UnlimitedAdapter mUnlimitAdapter;
    private ArrayList<String> mData;
    private int left;
    private int right;
    private int current;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mData = intent.getStringArrayListExtra(GridActivity.DATA);
        current = intent.getIntExtra(GridActivity.CURRENT, 0);
        left = current - 1;
        right = current + 1;
        String initUri = mData.get(current);
        setContentView(R.layout.pager_unlimited_acitvity_layout);
        mUnlimitViewPager = (UnlimitedViewPager) findViewById(R.id.viewPager);
        mUnlimitAdapter = new UnlimitedAdapter(this, mUnlimitViewPager, initUri, this);
        mUnlimitViewPager.setAdapter(mUnlimitAdapter);
        mUnlimitAdapter.scrolltoCurrent();
    }

    @Override
    public String getLeft() {
        if (left < 0) {
            return null;
        }
        String uri = mData.get(left);
        left--;
        return uri;
    }

    @Override
    public String getRight() {
        if (right > mData.size() - 1) {
            return null;
        }
        String uri = mData.get(right);
        right++;
        return uri;
    }

    @Override
    public ArrayList<String> getLeftArray() {
        return null;
    }

    @Override
    public ArrayList<String> getRightArrAy() {
        return null;
    }
}
