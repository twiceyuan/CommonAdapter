package com.twiceyuan.commonadapter.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.library.util.AdapterUtil;
import com.twiceyuan.commonadapter.library.util.FieldAnnotationParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public class SimpleListAdapter<T, VH extends CommonHolder<T>> extends BaseAdapter implements Adapter<T> {

    private LayoutInflater                       mInflater;
    private List<T>                              mData;
    private Class<? extends CommonHolder<T>>     mHolderClass;
    private Integer                              mLayoutId;
    private OnBindListener<T, VH>            mOnBindListener;

    public SimpleListAdapter(Context context, Class<? extends CommonHolder<T>> holderClass) {
        mHolderClass = holderClass;
        mData = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        mLayoutId = AdapterUtil.parseItemLayoutId(mHolderClass);
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
        //noinspection unchecked
        bindListener(convertView, position, (VH) holder);
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

    private void bindListener(View parentView, final int position, final VH holder) {
        if (mOnBindListener != null) {
            mOnBindListener.onBind(position, getItem(position), holder);
        }
    }

    public interface OnBindListener<T, VH> {
        void onBind(int position, T t, VH holder);
    }

    public void setOnBindListener(OnBindListener<T, VH> listener) {
        mOnBindListener = listener;
    }

    public interface OnItemClickListener<T> {
        void onClick(int position, T t);
    }
}
