package com.twiceyuan.commonadapter.library.holder;

import android.view.View;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public abstract class CommonHolder<T> {

    private View mItemView;

    public CommonHolder(View itemView) {
        mItemView = itemView;
    }

    public abstract void bindData(T t);

    public View getItemView() {
        return mItemView;
    }
}
