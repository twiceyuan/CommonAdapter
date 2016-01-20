package com.twiceyuan.commonadapter.library.holder;

import android.support.v7.widget.RecyclerView;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 *
 * common holder wrapper to RecyclerView holder
 */
public class CommonRecyclerHolder<T> extends RecyclerView.ViewHolder {

    private CommonHolder<T> mCommonHolder;

    public CommonRecyclerHolder(CommonHolder<T> commonHolder) {
        super(commonHolder.getItemView());
        mCommonHolder = commonHolder;
    }

    public CommonHolder<T> getCommonHolder() {
        return mCommonHolder;
    }
}
