package com.twiceyuan.commonadapter.library.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.library.util.AdapterUtil;
import com.twiceyuan.commonadapter.library.util.FieldAnnotationParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public class SimpleListAdapter<T> extends BaseAdapter implements Adapter<T> {

    private LayoutInflater                       mInflater;
    private List<T>                              mData;
    private Class<? extends CommonHolder<T>>     mHolderClass;
    private Integer                              mLayoutId;
    private Map<Integer, OnViewClickListener<T>> mClickListenerMap;

    public SimpleListAdapter(Context context, Class<? extends CommonHolder<T>> holderClass) {
        mHolderClass = holderClass;
        mData = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        mLayoutId = AdapterUtil.parseItemLayoutId(mHolderClass);
        mClickListenerMap = new HashMap<>();
    }

    @Override public int getCount() {
        return mData.size();
    }

    @Override public T getItem(int position) {
        return mData.get(position);
    }

    @Override public long getItemId(int position) {
        return mData.get(position).hashCode();
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(mLayoutId, parent, false);
            CommonHolder holder = AdapterUtil.createViewHolder(convertView, mHolderClass);
            FieldAnnotationParser.setViewFields(holder, convertView);
            convertView.setTag(holder);
        }
        //noinspection unchecked
        CommonHolder<T> holder = (CommonHolder<T>) convertView.getTag();
        holder.bindData(getItem(position));
        handlerClick(convertView, position);
        return convertView;
    }

    public void addAll(Collection<T> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void add(T t) {
        mData.add(t);
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public void remove(T t) {
        mData.remove(t);
        notifyDataSetChanged();
    }

    public void removeAll(Collection<T> ts) {
        mData.removeAll(ts);
        notifyDataSetChanged();
    }

    public void handlerClick(View parentView, final int position) {

        Set<Integer> ids = mClickListenerMap.keySet();
        for (final Integer id : ids) {
            View view = parentView.findViewById(id);
            if (view != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        mClickListenerMap.get(id).onClick(position, getItem(position));
                    }
                });
            }
        }
    }

    public interface OnViewClickListener<T> {
        void onClick(int position, T t);
    }

    public void setOnElementClickListener(@IdRes int id, OnViewClickListener<T> listener) {
        mClickListenerMap.put(id, listener);
        notifyDataSetChanged();
    }
}
