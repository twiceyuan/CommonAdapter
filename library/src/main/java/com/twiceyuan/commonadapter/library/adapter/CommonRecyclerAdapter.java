package com.twiceyuan.commonadapter.library.adapter;

import android.content.Context;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public class CommonRecyclerAdapter<T> extends CommonAdapter<T, CommonHolder<T>> {

    public CommonRecyclerAdapter(Context context, Class<? extends CommonHolder<T>> holderClass) {
        super(context, holderClass);
    }

    public CommonRecyclerAdapter(Context context, ViewTypeMapper mapper) {
        super(context, mapper);
    }
}
