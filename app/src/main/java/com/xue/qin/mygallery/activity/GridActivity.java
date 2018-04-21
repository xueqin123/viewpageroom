package com.xue.qin.mygallery.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xue.qin.mygallery.R;
import com.xue.qin.mygallery.Utils.SystemUitls;
import com.xue.qin.mygallery.adapter.GridAdapter;
import com.xue.qin.mygallery.interfaces.OnItemClickListener;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by xue.qin on 2018/4/21.
 */

public class GridActivity extends Activity implements OnItemClickListener {
    public static final String DATA = "data";
    public static final String CURRENT = "current";
    private GridAdapter mAdapter;
    private RecyclerView mView;
    private ArrayList<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity_layout);
        mView = (RecyclerView) findViewById(R.id.recyclerView);
        mView.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter = new GridAdapter(this, mData,this);
        mView.setAdapter(mAdapter);
        SystemUitls.requestStoragePermissions(this);
        initDate();
    }
    private void initDate() {
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media._ID};
        String orderBy = MediaStore.Images.Media.DATE_TAKEN + " DESC";
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, orderBy);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String path = cursor.getString(0);
                    if (path == null) {
                        continue;
                    }
                    File file = new File(path);
                    int id = cursor.getInt(2);
                    if (file != null && file.exists()) {
                        Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + id);
                        mData.add(uri.toString());
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,PagerActivity.class);
        intent.putExtra(CURRENT,position);
        intent.putStringArrayListExtra(DATA,mData);
        startActivity(intent);
    }
}
