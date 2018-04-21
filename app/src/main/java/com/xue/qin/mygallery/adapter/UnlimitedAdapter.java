package com.xue.qin.mygallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xue.qin.mygallery.R;
import com.xue.qin.mygallery.interfaces.DataProvider;
import com.xue.qin.mygallery.views.UnlimitedViewPager;

import java.util.ArrayList;

/**
 * Created by xue.qin on 2018/4/21.
 */

public class UnlimitedAdapter extends android.support.v4.view.PagerAdapter {
    private static final int MAX_CAPACITY = 10000;
    private Context mContext;
    private String[] mData = new String[MAX_CAPACITY];
    private LayoutInflater mInflater;
    private UnlimitedViewPager mViewPager;
    private int initCursot = MAX_CAPACITY / 2;
    private int leftCursor = MAX_CAPACITY / 2 - 1;
    private int rightCursor = MAX_CAPACITY / 2 + 1;
    private DataProvider mDataProvider;

    public UnlimitedAdapter(Context context, UnlimitedViewPager viewPager, String initUri, DataProvider dataProvider) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mDataProvider = dataProvider;
        mViewPager = viewPager;
        init(initUri);
    }

    private void init(String initUri) {
        mData[initCursot] = initUri;
        addLeft(mDataProvider.getLeft());
        addRight(mDataProvider.getRight());
    }

    public void scrolltoCurrent() {
        mViewPager.setCurrentItem(initCursot);
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        updateData(position);
        if (mData[position] != null) {
            View view = mInflater.inflate(R.layout.pager_adapter_item, null, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Glide.with(mContext)
                    .load(mData[position])
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
            container.addView(view);
            return view;
        } else {
            return null;
        }
    }

    private void updateData(int position) {
        String uri = null;
        if (position == leftCursor + 1) {
            uri = mDataProvider.getLeft();
            if (uri != null) {
                addLeft(uri);
            } else {
                mViewPager.setRealLeftPosition(position);
            }
        } else if (position == rightCursor - 1) {
            uri = mDataProvider.getRight();
            if (uri != null) {
                addRight(uri);
            } else {
                mViewPager.setRealRightPosition(position);
            }
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void addLeft(String uri) {
        if (leftCursor == 0 || uri == null) {
            mViewPager.setRealLeftPosition(leftCursor + 1);
            return;
        }
        mData[leftCursor] = uri;
        leftCursor--;
    }

    public void addRight(String uri) {
        if (rightCursor == MAX_CAPACITY - 1 || uri == null) {
            mViewPager.setRealRightPosition(rightCursor - 1);
        }
        mData[rightCursor] = uri;
        rightCursor++;
    }

    public void addLeft(ArrayList<String> uris) {
        for (String uri : uris) {
            mData[leftCursor] = uri;
            leftCursor--;
            if (leftCursor < 0) {
                mViewPager.setRealLeftPosition(leftCursor);
                break;
            }
        }
    }

    public void addRight(ArrayList<String> uris) {
        for (String uri : uris) {
            mData[rightCursor] = uri;
            rightCursor++;
            if (rightCursor > MAX_CAPACITY - 1) {
                mViewPager.setRealRightPosition(rightCursor);
                break;
            }
        }
    }
}
