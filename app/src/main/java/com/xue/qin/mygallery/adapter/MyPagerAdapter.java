package com.xue.qin.mygallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.xue.qin.mygallery.R;

import java.util.List;

/**
 * Created by xue.qin on 2018/4/21.
 */

public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mData;
    private LayoutInflater mInflater;

    public MyPagerAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.pager_adapter_item, null, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Glide.with(mContext)
                .load(mData.get(position))
                .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
