package com.twiceyuan.commonadapter.library.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.library.holder.CommonRecyclerHolder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by twiceYuan on 4/16/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 * <p/>
 * 可以设置 Footer 和 Header 的 CommonAdapter
 */
public class WrapperAdapter<T, Holder extends CommonHolder<T>> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "WrapperAdapter";

    private static final int HEADER_HOLDER = 10001;
    private static final int FOOTER_HOLDER = 10002;

    private CommonAdapter<T, Holder> mChildAdapter;

    private View mHeaderView;
    private View mFooterView;

    final LinearLayoutCompat.LayoutParams mLayoutParams = new LinearLayoutCompat.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);

    public WrapperAdapter(Context context, Class<Holder> holderClass) {
        mChildAdapter = new CommonAdapter<>(context, holderClass);
    }

    public WrapperAdapter(CommonAdapter<T, Holder> childAdapter) {
        mChildAdapter = childAdapter;
        childAdapter.notifyDataSetChanged();
        List<RecyclerView.AdapterDataObserver> observers = getObserverFromAdapter(childAdapter);
        if (observers != null) {
            for (RecyclerView.AdapterDataObserver observer : observers) {
                registerAdapterDataObserver(observer);
            }
        }
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_HOLDER) {
            return new HeaderHolder(mHeaderView);
        }
        if (viewType == FOOTER_HOLDER) {
            return new FooterHolder(mFooterView);
        }
        return mChildAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override public int getItemViewType(int position) {
        if (position == 0 && mHeaderView != null) {
            return HEADER_HOLDER;
        }
        if (position == getItemCount() - 1 && mFooterView != null) {
            return FOOTER_HOLDER;
        }
        return super.getItemViewType(position);
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        mHeaderView.setLayoutParams(mLayoutParams);
        notifyItemChanged(0);
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        mFooterView.setLayoutParams(mLayoutParams);
        notifyItemChanged(getItemCount() - 1);
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mHeaderView != null) {
            position -= 1;
            if (position == -1) {
                return;
            }
        }
        if (mFooterView != null) {
            if (position == mChildAdapter.getItemCount()) {
                return;
            }
        }
        //noinspection unchecked
        mChildAdapter.onBindViewHolder((CommonRecyclerHolder<T>) holder, position);
    }

    @Override public int getItemCount() {
        int count = mChildAdapter.getItemCount();
        return count + (mHeaderView == null ? 0 : 1) + (mFooterView == null ? 0 : 1);
    }

    public static class FooterHolder extends RecyclerView.ViewHolder {
        public FooterHolder(View itemView) {
            super(itemView);
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    @SuppressWarnings("unused") public void addAll(Collection<? extends T> list) {
        mChildAdapter.addAll(list);
    }

    @SuppressWarnings("unused") public void add(T t) {
        mChildAdapter.add(t);
    }

    @SuppressWarnings("unused") public void clear() {
        mChildAdapter.clear();
    }

    @SuppressWarnings("unused") public void remove(T t) {
        mChildAdapter.remove(t);
    }

    @SuppressWarnings("unused") public void removeAll(Collection<? extends T> ts) {
        mChildAdapter.removeAll(ts);
    }

    public List<T> getData() {
        return mChildAdapter.getData();
    }

    @SuppressWarnings("unused") public void setOnBindListener(CommonAdapter.OnBindListener<T, Holder> listener) {
        mChildAdapter.setOnBindListener(listener);
    }

    @SuppressWarnings("unused") public void setOnItemClickListener(CommonAdapter.OnItemClickListener<T> listener) {
        mChildAdapter.setOnItemClickListener(listener);
    }

    public List<RecyclerView.AdapterDataObserver> getObserverFromAdapter(RecyclerView.Adapter adapter) {
        Class<RecyclerView.Adapter> adapterClass = RecyclerView.Adapter.class;
        try {
            Field observableFiled = adapterClass.getDeclaredField("mObservable");
            observableFiled.setAccessible(true);
            Object observable = observableFiled.get(adapter);
            Field observersField = observable.getClass().getDeclaredField("mObservers");
            observersField.setAccessible(true);
            //noinspection unchecked
            return (ArrayList<RecyclerView.AdapterDataObserver>) observableFiled.get(observable);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
