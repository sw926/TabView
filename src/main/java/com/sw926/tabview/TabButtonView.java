package com.sw926.tabview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by sunwei on 9/20/15.
 */
public class TabButtonView extends FrameLayout {

    private TextView mTvTab;
    private TextView mTvNotificationCount;
    private ImageView mIvIcon;
    private ImageView mIvReadPoint;

    private Drawable mDrawable;
    private String mText;

    public TabButtonView(Context context) {
        this(context, null);
    }

    public TabButtonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_tab_button, this);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TabButtonView);
        mDrawable = typedArray.getDrawable(R.styleable.TabButtonView_tabDrawable);
        mText = typedArray.getString(R.styleable.TabButtonView_tabText);
        ColorStateList colorStateList = typedArray.getColorStateList(R.styleable.TabButtonView_tabButtonTextColor);
        typedArray.recycle();

        mIvIcon = (ImageView) findViewById(R.id.iv_icon);
        mTvTab = (TextView) findViewById(R.id.tv_tab);
        mIvReadPoint = (ImageView) findViewById(R.id.ivRedPoint);
        mTvNotificationCount = (TextView) findViewById(R.id.tv_notification_count);
        if (colorStateList != null) {
            mTvTab.setTextColor(colorStateList);
        }
    }

    public void setTabTextColor(ColorStateList list) {
        mTvTab.setTextColor(list);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setTabText(mText);
        setTabDrawable(mDrawable);
    }

    public void setNotificationCount(int count) {
        if (count > 0) {
            mTvNotificationCount.setVisibility(VISIBLE);
            mTvNotificationCount.setText(String.valueOf(count));
        } else {
            mTvNotificationCount.setVisibility(GONE);
            mTvNotificationCount.setText("");
        }
    }

    public void setShowRedPoint(boolean show) {
        mIvReadPoint.setVisibility(show ? VISIBLE : GONE);
    }

    public void setTabText(String text) {
        mText = text;
        mTvTab.setText(mText);
    }

    public void setTabDrawableId(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        setTabDrawable(drawable);
    }

    public void setTabDrawable(Drawable drawable) {
        mDrawable = drawable;
        if (mDrawable != null) {
            mIvIcon.setImageDrawable(drawable);
        } else {
            mIvIcon.setImageDrawable(null);
        }
    }
}
