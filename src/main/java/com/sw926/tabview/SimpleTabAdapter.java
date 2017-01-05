package com.sw926.tabview;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunwei on 2016/12/29.
 * Project: neihan
 */

public class SimpleTabAdapter implements TabView.TabViewAdapter {

    public interface ViewProvider {
        View getView();
    }

    private final List<String> mTitles = new ArrayList<>();
    private final List<ViewProvider> mViewProviders = new ArrayList<>();
    private final List<Integer> mIcons = new ArrayList<>();

    public void addTab(String title, int icon, ViewProvider viewProvider) {
        mTitles.add(title);
        mIcons.add(icon);
        mViewProviders.add(viewProvider);
    }

    @Override
    public int getCount() {
        return mViewProviders.size();
    }

    @Override
    public int getIcon(int index) {
        return mIcons.get(index);
    }

    @Override
    public String getTitle(int index) {
        return mTitles.get(index);
    }

    @Override
    public View getContentView(int index) {
        return mViewProviders.get(index).getView();
    }
}
