package com.twiceyuan.commonadapter.library.holder;

import com.twiceyuan.commonadapter.library.adapter.CommonAdapter;

/**
 * Created by twiceYuan on 21/02/2017.
 * <p>
 * 复杂的 CommonHolder 传入了更多参数供适配器 Holder 中使用
 */
public abstract class ComplexHolder<T> extends CommonHolder<T> {

    /**
     * @param t        数据
     * @param position 位置
     * @param adapter  所在适配器
     */
    public abstract void bindData(T t, int position, CommonAdapter<T, ? extends CommonHolder<T>> adapter);

    @Override
    public void bindData(T t) {
        // ignore
    }
}
