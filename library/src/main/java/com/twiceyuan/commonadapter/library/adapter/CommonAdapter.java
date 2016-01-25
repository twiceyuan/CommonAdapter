package com.twiceyuan.commonadapter.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.library.holder.CommonRecyclerHolder;
import com.twiceyuan.commonadapter.library.util.AdapterUtil;
import com.twiceyuan.commonadapter.library.util.FieldAnnotationParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 *
 * 通用 RecyclerView Adapter
 */
public class CommonAdapter<T, VH extends CommonHolder<T>> extends RecyclerView.Adapter<CommonRecyclerHolder<T>>
        implements DataManager<T> {

    private Class<? extends CommonHolder<T>> mHolderClass;
    private List<T>                          mData;
    private LayoutInflater                   mInflater;
    private Integer                          mLayoutId;
    private OnBindListener<T, VH>            mOnBindListener;
    private OnItemClickListener<T>           mItemClickListener;

    public CommonAdapter(Context context, Class<? extends CommonHolder<T>> holderClass) {
        mHolderClass = holderClass;
        mData = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        mLayoutId = AdapterUtil.parseItemLayoutId(holderClass);
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    @Override public CommonRecyclerHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mLayoutId, parent, false);
        //noinspection unchecked
        CommonRecyclerHolder<T> holder = new CommonRecyclerHolder<>(AdapterUtil.createViewHolder(view, mHolderClass));
        FieldAnnotationParser.setViewFields(holder.getCommonHolder(), view);
        return holder;
    }

    @Override public int getItemCount() {
        return mData.size();
    }

    @Override public void onBindViewHolder(CommonRecyclerHolder<T> holder, int position) {
        holder.getCommonHolder().bindData(mData.get(position));
        //noinspection unchecked
        bindListener(holder.itemView, position, (VH) holder.getCommonHolder());
    }

    public void addAll(Collection<T> list) {
        mData.addAll(list);
    }

    public void add(T t) {
        mData.add(t);
    }

    public void clear() {
        mData.clear();
    }

    public void remove(T t) {
        mData.remove(t);
    }

    public void removeAll(Collection<T> ts) {
        mData.removeAll(ts);
    }

    private void bindListener(View parentView, final int position, final VH holder) {

        if (mItemClickListener != null) {
            parentView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    mItemClickListener.onClick(position, getItem(position));
                }
            });
        }

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

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        mItemClickListener = listener;
        notifyDataSetChanged();
    }
}
