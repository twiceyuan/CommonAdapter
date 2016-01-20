package com.twiceyuan.commonadapter.library.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public class SimpleRecyclerAdapter<T> extends RecyclerView.Adapter<CommonRecyclerHolder<T>>
        implements Adapter<T> {

    private Class<? extends CommonHolder<T>>     mHolderClass;
    private List<T>                              mData;
    private LayoutInflater                       mInflater;
    private Integer                              mLayoutId;
    private Map<Integer, OnViewClickListener<T>> mClickListenerMap;
    private OnViewClickListener<T>               mItemClickListener;

    public SimpleRecyclerAdapter(Context context, Class<? extends CommonHolder<T>> holderClass) {
        mHolderClass = holderClass;
        mData = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        mLayoutId = AdapterUtil.parseItemLayoutId(holderClass);
        mClickListenerMap = new HashMap<>();
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
        handlerClick(holder.itemView, position);
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

        if (mItemClickListener != null) {
            parentView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    mItemClickListener.onClick(position, getItem(position));
                }
            });
        }

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

    public void setOnItemClickListener(OnViewClickListener<T> listener) {
        mItemClickListener = listener;
        notifyDataSetChanged();
    }

}
