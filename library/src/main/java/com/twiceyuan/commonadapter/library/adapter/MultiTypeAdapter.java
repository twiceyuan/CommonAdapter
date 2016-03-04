package com.twiceyuan.commonadapter.library.adapter;

import android.content.Context;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public class MultiTypeAdapter extends CommonAdapter<ViewTypeItem, CommonHolder<ViewTypeItem>> {
    public MultiTypeAdapter(Context context, ViewTypeMapper mapper) {
        super(context, mapper);
    }
}
