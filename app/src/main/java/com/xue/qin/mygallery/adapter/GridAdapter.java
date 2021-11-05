package com.xue.qin.mygallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xue.qin.mygallery.R;
import com.xue.qin.mygallery.interfaces.OnItemClickListener;
import com.xue.qin.mygallery.viewHolder.GridItemViewHolder;

import java.util.List;

/**
 * Created by xue.qin on 2018/4/21.
 */

public class GridAdapter extends RecyclerView.Adapter<GridItemViewHolder> implements View.OnClickListener{
    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mData;
    private OnItemClickListener mListener;

    public GridAdapter(Context context, List<String> list, OnItemClickListener listener) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mData = list;
        mListener = listener;
    }

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GridItemViewHolder holder;
        View view = mInflater.inflate(R.layout.grid_adapter_item, parent, false);
        holder = new GridItemViewHolder(view);
        holder.imageView = (ImageView) view.findViewById(R.id.imageView);
        return holder;
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        ImageView imageView = holder.imageView;
        Glide.with(mContext)
                .load(mData.get(position))
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        mListener.onItemClick(position);
    }
}
