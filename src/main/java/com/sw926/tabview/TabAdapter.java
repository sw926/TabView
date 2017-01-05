package com.sw926.tabview;

import android.view.View;

/**
 * Created by sunwei on 2016/12/29.
 * Project: neihan
 */

public interface TabAdapter {
    int getCount();

    int getIcon(int index);

    String getTitle(int index);

    View getContentView(int index);

}
