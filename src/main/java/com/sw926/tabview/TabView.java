package com.sw926.tabview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by sunwei on 15/11/4.
 */
public class TabView extends RelativeLayout {

    private LinearLayout mTabs;
    private FrameLayout mLayoutContent;

    private final List<TabButtonView> mTabButtonViews = new ArrayList<>();
    private final SparseArray<View> mContentViews = new SparseArray<>();

    private TabViewAdapter mTabViewAdapter;
    private int mCurrentItem = -1;
    private onTabSelectedListener mOnTabSelectedListener;
    private ColorStateList mTabTextColor;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_tab_view, this);
        mTabs = (LinearLayout) findViewById(R.id.custom_tabs);
        mLayoutContent = (FrameLayout) findViewById(R.id.layout_content);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TabView);

        mTabTextColor = typedArray.getColorStateList(R.styleable.TabView_tabTextColor);

        typedArray.recycle();


    }


    public void setAdapter(TabViewAdapter adapter) {
        mTabViewAdapter = adapter;

        mContentViews.clear();

        mTabs.removeAllViews();
        mTabButtonViews.clear();
        for (int i = 0; i < mTabViewAdapter.getCount(); i++) {
            TabButtonView view = (TabButtonView) LayoutInflater.from(getContext()).inflate(R.layout.layout_tab, mTabs, false);
            view.setTabDrawableId(mTabViewAdapter.getIcon(i));
            view.setTabText(mTabViewAdapter.getTitle(i));
            if (mTabTextColor != null) {
                view.setTabTextColor(mTabTextColor);
            }
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setCurrentItem(finalI);
                }
            });
            mTabs.addView(view);
            mTabButtonViews.add(view);
        }
    }

    public void setCurrentItem(int index) {
        if (mCurrentItem == index) {
            return;
        }
        mCurrentItem = index;
        for (int i = 0; i < mTabButtonViews.size(); i++) {
            if (i != mCurrentItem) {
                mTabButtonViews.get(i).setSelected(false);
            } else {
                mTabButtonViews.get(i).setSelected(true);
            }
        }

        for (int i = 0; i < mContentViews.size(); i++) {
            mContentViews.valueAt(i).setVisibility(INVISIBLE);
        }

        View view = mContentViews.get(index);
        if (view == null) {
            view = mTabViewAdapter.getContentView(index);
            if (view != null) {
                mContentViews.append(index, view);
                mLayoutContent.addView(view);
            }
        }

        if (view != null) {
            view.setVisibility(VISIBLE);
        }

        if (mOnTabSelectedListener != null) {
            mOnTabSelectedListener.onTabSelected(index);
        }
    }

    public void setUnReadCount(int tabIndex, int count) {
        mTabButtonViews.get(tabIndex).setNotificationCount(count);
    }

    public void setShowRedPoint(int tabIndex, boolean show) {
        mTabButtonViews.get(tabIndex).setShowRedPoint(show);
    }

    public View getContentView(int index) {
        return mContentViews.get(index);
    }

    public int getCurrentItem() {
        return mCurrentItem;
    }

    public void setOnTabSelectedListener(onTabSelectedListener onTabSelectedListener) {
        mOnTabSelectedListener = onTabSelectedListener;
    }

    public interface TabViewAdapter {
        int getCount();

        View getContentView(int index);

        int getIcon(int index);

        String getTitle(int index);
    }

    public interface onTabSelectedListener {
        void onTabSelected(int index);
    }

}
