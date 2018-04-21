package com.xue.qin.mygallery.views;

/**
 * Created by xue.qin on 2018/4/21.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * Created by qinxue on 2018/1/15.
 */

public class UnlimitedViewPager extends HackyViewPager {
    private int mLeftPosition;
    private int mRightPosition;
    private int mWidth;
    private float mLastX = -1;

    public UnlimitedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getClientWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float dx = x - mLastX;
        mLastX = x;
        if (getCurrentItem() == mRightPosition && dx < 0 || getCurrentItem() == mLeftPosition && dx > 0) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float dx = x - mLastX;
        mLastX = x;
        if (getCurrentItem() == mRightPosition && dx < 0 || getCurrentItem() == mLeftPosition && dx > 0) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mLastX = ev.getX();
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setRealLeftPosition(int leftPosition) {
        mLeftPosition = leftPosition;
    }

    public void setRealRightPosition(int rightPosition) {
        mRightPosition = rightPosition;
    }

    private int getClientWidth() {
        return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }
}