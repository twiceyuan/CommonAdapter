package com.twiceyuan.commonadapter.library.holder;

import android.view.View;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public abstract class CommonHolder<T> {

    private View mItemView;

    public abstract void bindData(T t);

    public void setItemView(View view) {
        mItemView = view;
    }

    public View getItemView() {
        return mItemView;
    }

    public void initSingleton() {
    }

    public void initView() {
    }
}
