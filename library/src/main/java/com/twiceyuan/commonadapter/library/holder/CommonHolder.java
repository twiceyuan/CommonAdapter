package com.twiceyuan.commonadapter.library.holder;

import androidx.annotation.LayoutRes;
import android.view.View;

import com.twiceyuan.commonadapter.library.LayoutId;

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

    public @LayoutRes int getLayoutId() {
        LayoutId layoutId = this.getClass().getAnnotation(LayoutId.class);
        if (layoutId != null) {
            return layoutId.value();
        } else {
            return -1;
        }
    }

    public void initSingleton() {
    }

    public void initView() {
    }
}
