package com.twiceyuan.commonadapter.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.library.util.AdapterUtil;
import com.twiceyuan.commonadapter.library.util.FieldAnnotationParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 * <p/>
 * 通用 ListView Adapter
 */
public class CommonListAdapter<T, VH extends CommonHolder<T>> extends BaseAdapter implements DataManager<T> {

    private LayoutInflater                   mInflater;
    private List<T>                          mData;
    private Class<? extends CommonHolder<T>> mHolderClass;
    private Integer                          mLayoutId;
    private OnBindListener<T, VH>            mOnBindListener;

    private Map<String, Object> mSingletonCache = new ConcurrentHashMap<>();

    public CommonListAdapter(Context context, Class<? extends CommonHolder<T>> holderClass) {
        mHolderClass = holderClass;
        mData = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        mLayoutId = AdapterUtil.parseItemLayoutId(mHolderClass);
    }

    public static <T, VH extends CommonHolder<T>> CommonListAdapter<T, VH> create(Context context, Class<VH> vhClass) {
        return new CommonListAdapter<>(context, vhClass);
    }

    public CommonListAdapter<T, VH> attach(ListView listView) {
        listView.setAdapter(this);
        return this;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(mLayoutId, parent, false);
            CommonHolder holder = AdapterUtil.createViewHolder(convertView, mHolderClass);
            AdapterUtil.setupAdapterSingleton(mSingletonCache, holder);
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

    public CommonListAdapter<T, VH> addAll(Collection<? extends T> list) {
        mData.addAll(list);
        return this;
    }

    public CommonListAdapter<T, VH> add(T t) {
        mData.add(t);
        return this;
    }

    public CommonListAdapter<T, VH> clear() {
        mData.clear();
        return this;
    }

    public void remove(T t) {
        mData.remove(t);
    }

    public void removeAll(Collection<? extends T> ts) {
        mData.removeAll(ts);
    }

    @Override
    public List<T> getData() {
        return mData;
    }

    private void bindListener(View parentView, final int position, final VH holder) {
        if (mOnBindListener != null) {
            mOnBindListener.onBind(parentView, position, getItem(position), holder);
        }
    }

    public CommonListAdapter<T, VH> setOnBindListener(OnBindListener<T, VH> listener) {
        mOnBindListener = listener;
        return this;
    }

    public interface OnBindListener<T, VH> {
        void onBind(View parentView, int position, T t, VH holder);
    }

    @SuppressWarnings("unused")
    public interface OnItemClickListener<T> {
        void onClick(int position, T t);
    }
}
