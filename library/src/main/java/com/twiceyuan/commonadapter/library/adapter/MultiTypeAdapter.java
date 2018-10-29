package com.twiceyuan.commonadapter.library.adapter;

import android.content.Context;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 * <p>
 * 多种 View Type 的 CommonAdapter 的直接继承。避免在使用的时候用错泛型
 */
public class MultiTypeAdapter<ItemType> extends CommonAdapter<ItemType, CommonHolder<ItemType>> {

    private Map<OnBindListener, Class> mOnBindListeners;

    public MultiTypeAdapter(Context context, ViewTypeMapper mapper) {
        super(context, mapper);
        mOnBindListeners = new HashMap<>();
    }

    public MultiTypeAdapter(Context context) {
        super(context);
        mOnBindListeners = new HashMap<>();
    }

    public <DataType> void registerViewType(Class<DataType> dataClass, Class<? extends CommonHolder<DataType>> holderClass) {
        super.registerViewType(dataClass, holderClass);
    }

    /**
     * 可以让用户指定实体或者 Holder 的类型来回调 Holder 完成事件绑定
     *
     * @param viewTypeClass viewType 所在实体的类型 class
     * @param holderClass   holder class
     * @param listener      holder 绑定时的监听器
     * @param <T>           实体类型
     * @param <VH>          holder 类型
     */
    @SuppressWarnings("unused")
    public <T extends ViewTypeItem, VH extends CommonHolder>
    void addHolderListener(final Class<T> viewTypeClass,
                           final Class<VH> holderClass,
                           final OnBindListener<T, VH> listener) {

        if (viewTypeClass != null) {
            mOnBindListeners.put(listener, viewTypeClass);
        }
        if (holderClass != null) {
            mOnBindListeners.put(listener, holderClass);
        }

        refreshListener();
    }

    /**
     * 监听器发生变动
     */
    private void refreshListener() {
        super.setOnBindListener(new OnBindListener<ItemType, CommonHolder<ItemType>>() {
            @Override
            public void onBind(int position, ItemType item, CommonHolder<ItemType> holder) {
                Set<OnBindListener> listeners = mOnBindListeners.keySet();
                for (OnBindListener listener : listeners) {
                    Class argClass = mOnBindListeners.get(listener);
                    if (item.getClass() == argClass || holder.getClass() == argClass) {
                        //noinspection unchecked
                        listener.onBind(position, item, holder);
                    }
                }
            }
        });
    }

    /**
     * 移除监听器
     *
     * @param listener 需要移除的监听器对象
     */
    public void removeHolderListener(OnBindListener listener) {
        mOnBindListeners.remove(listener);
        refreshListener();
    }

    @Override
    public CommonAdapter<ItemType, CommonHolder<ItemType>> setOnBindListener(OnBindListener<ItemType, CommonHolder<ItemType>> listener) {
        throw new RuntimeException("Don't configure onBindListener in MultiTypeAdapter, please use addHolderListener");
    }
}
