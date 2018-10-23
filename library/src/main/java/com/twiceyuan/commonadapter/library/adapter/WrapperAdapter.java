package com.twiceyuan.commonadapter.library.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.library.holder.CommonRecyclerHolder;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Created by twiceYuan on 4/16/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 * <p/>
 * 可以设置 Footer 和 Header 的 CommonAdapter
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class WrapperAdapter<T, Holder extends CommonHolder<T>> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "WrapperAdapter";

    private HeaderHolder mHeaderHolder;
    private FooterHolder mFooterHolder;

    final RecyclerView.LayoutParams mLayoutParams = new RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);

    private CommonAdapter<T, Holder> mChildAdapter;

    public WrapperAdapter(Context context, Class<Holder> holderClass) {
        mChildAdapter = new CommonAdapter<>(context, holderClass);
    }

    /**
     * 根据一个 子适配器来构造 WrapperAdapter. 需要传入 RecyclerView 的原因是需要获取其 DataSetObserver,
     * 让子适配器也能通知数据更新.
     *
     * @param childAdapter childAdapter
     * @param recyclerView recyclerView
     */
    public WrapperAdapter(CommonAdapter<T, Holder> childAdapter, RecyclerView recyclerView) {
        mChildAdapter = childAdapter;
        childAdapter.registerAdapterDataObserver(getObserverFromRecyclerView(recyclerView));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: " + viewType);
        if (viewType == getHeaderType()) {
            return mHeaderHolder;
        }
        if (viewType == getFooterType()) {
            return mFooterHolder;
        }
        return mChildAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && mHeaderHolder != null) {
            return mHeaderHolder.hashCode();
        }
        if (position == getItemCount() - 1 && mFooterHolder != null) {
            return mFooterHolder.hashCode();
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == getHeaderType() ||
                            getItemViewType(position) == getFooterType() ?
                            gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }

    private int getHeaderType() {
        return mHeaderHolder == null ? -1 : mHeaderHolder.hashCode();
    }

    private int getFooterType() {
        return mFooterHolder == null ? -1 : mFooterHolder.hashCode();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        View itemView = holder.itemView;
        ViewGroup.LayoutParams lp = itemView.getLayoutParams();
        if (lp == null) {
            return;
        }

        if (holder instanceof HeaderHolder || holder instanceof FooterHolder) {

            if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    public void setHeaderView(View headerView) {
        if (mHeaderHolder != null && mHeaderHolder.itemView == headerView) {
            return;
        }

        headerView.setLayoutParams(mLayoutParams);
        mHeaderHolder = new HeaderHolder(headerView);
        notifyDataSetChanged();
    }

    public void setFooterView(View footerView) {
        if (mFooterHolder != null && mFooterHolder.itemView == footerView) {
            return;
        }

        footerView.setLayoutParams(mLayoutParams);
        mFooterHolder = new FooterHolder(footerView);
        notifyDataSetChanged();
    }

    public void removeHeaderView() {
        mHeaderHolder = null;
        notifyDataSetChanged();
    }

    public void removeFooterView() {
        mFooterHolder = null;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (mHeaderHolder != null) {
            position -= 1;
            if (position == -1) {
                return;
            }
        }
        if (mFooterHolder != null) {
            if (position == mChildAdapter.getItemCount()) {
                return;
            }
        }
        //noinspection unchecked
        mChildAdapter.onBindViewHolder((CommonRecyclerHolder<T>) holder, position);
    }

    @Override
    public int getItemCount() {
        int count = mChildAdapter.getItemCount();
        return count + (mHeaderHolder == null ? 0 : 1) + (mFooterHolder == null ? 0 : 1);
    }

    public T getItem(int position) {
        return mChildAdapter.getItem(position);
    }

    @SuppressWarnings("unused")
    public void addAll(Collection<? extends T> list) {
        mChildAdapter.addAll(list);
    }

    @SuppressWarnings("unused")
    public void add(T t) {
        mChildAdapter.add(t);
    }

    @SuppressWarnings("unused")
    public void clear() {
        mChildAdapter.clear();
    }

    @SuppressWarnings("unused")
    public void remove(T t) {
        mChildAdapter.remove(t);
    }

    @SuppressWarnings("unused")
    public void removeAll(Collection<? extends T> ts) {
        mChildAdapter.removeAll(ts);
    }

    public CommonAdapter<T, Holder> getChildAdapter() {
        return mChildAdapter;
    }

    public List<T> getData() {
        return mChildAdapter.getData();
    }

    @SuppressWarnings("unused")
    public void setOnBindListener(CommonAdapter.OnBindListener<T, Holder> listener) {
        mChildAdapter.setOnBindListener(listener);
    }

    @SuppressWarnings("unused")
    public void setOnItemClickListener(CommonAdapter.OnItemClickListener<T> listener) {
        mChildAdapter.setOnItemClickListener(listener);
    }

    public RecyclerView.AdapterDataObserver getObserverFromRecyclerView(RecyclerView recyclerView) {
        try {
            Field observerField = recyclerView.getClass().getDeclaredField("mObserver");
            observerField.setAccessible(true);
            return (RecyclerView.AdapterDataObserver) observerField.get(recyclerView);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
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
}
