package com.twiceyuan.commonadapter.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.commonadapter.library.holder.CommonRecyclerHolder;
import com.twiceyuan.commonadapter.library.holder.ComplexHolder;
import com.twiceyuan.commonadapter.library.util.AdapterUtil;
import com.twiceyuan.commonadapter.library.util.FieldAnnotationParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 * <p>
 * 通用 RecyclerView Adapter
 */
@SuppressWarnings("unused")
public class CommonAdapter<T, VH extends CommonHolder<T>> extends RecyclerView.Adapter<CommonRecyclerHolder<T>>
        implements DataManager<T> {

    private List<T>                          mData;
    private LayoutInflater                   mInflater;
    private OnBindListener<T, VH>            mOnBindListener;
    private OnItemClickListener<T>           mItemClickListener;
    private ViewTypeMapper                   mViewTypeMapper;
    private Class<? extends CommonHolder<T>> mHolderClass;

    private Map<Class<?>, Class<? extends CommonHolder<?>>> mHolderMap;

    private Map<Class<? extends CommonHolder>, Integer> mHolderLayouts; // viewHolder => layout id
    private SparseArray<Class<? extends CommonHolder>>  mViewTypeHolders; // viewType => CommonHolder

    private Map<String, Object> mAdapterSingletonCache = new ConcurrentHashMap<>();

    public CommonAdapter(Context context, Class<? extends CommonHolder<T>> holderClass) {
        mHolderClass = holderClass;
        mData = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    CommonAdapter(Context context, ViewTypeMapper mapper) {
        mViewTypeMapper = mapper;
        mData = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        mHolderLayouts = new HashMap<>();
        mViewTypeHolders = new SparseArray<>();
    }

    CommonAdapter(Context context) {
        mHolderMap = new ConcurrentHashMap<>();
        mData = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        mHolderLayouts = new HashMap<>();
        mViewTypeHolders = new SparseArray<>();
    }

    public static <T, VH extends CommonHolder<T>> CommonAdapter<T, VH> create(Context context, Class<VH> vhClass) {
        return new CommonAdapter<>(context, vhClass);
    }

    public CommonAdapter<T, VH> attach(RecyclerView recyclerView) {
        recyclerView.setAdapter(this);
        return this;
    }

    @SuppressWarnings("WeakerAccess")
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public CommonRecyclerHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {

        // 如果配置了 HolderClass 则直接通过反射创建
        if (mHolderClass != null) {
            CommonHolder viewHolder = AdapterUtil.createViewHolder(mHolderClass);
            View view = mInflater.inflate(viewHolder.getLayoutId(), parent, false);
            viewHolder.setItemView(view);
            AdapterUtil.setupAdapterSingleton(mAdapterSingletonCache, viewHolder);
            //noinspection unchecked
            CommonRecyclerHolder<T> holder = new CommonRecyclerHolder<>(viewHolder);
            FieldAnnotationParser.setViewFields(holder.getCommonHolder(), view);
            viewHolder.initView();
            return holder;
        }

        // 如果配置的是 HolderClass 和 ViewType 的映射，则通过 viewType 参数获得 holderClass 后执行相同操作
        if (mViewTypeMapper != null || mHolderMap != null) {
            Class<? extends CommonHolder> holderClass = mViewTypeHolders.get(viewType);
            Integer layoutId = mHolderLayouts.get(holderClass);
            CommonHolder viewHolder = AdapterUtil.createViewHolder(holderClass);
            if (layoutId == null) {
                layoutId = viewHolder.getLayoutId();
                mHolderLayouts.put(holderClass, layoutId);
            }
            View view = mInflater.inflate(layoutId, parent, false);
            viewHolder.setItemView(view);
            AdapterUtil.setupAdapterSingleton(mAdapterSingletonCache, viewHolder);
            //noinspection unchecked
            CommonRecyclerHolder<T> holder = new CommonRecyclerHolder<>(viewHolder);
            FieldAnnotationParser.setViewFields(holder.getCommonHolder(), view);
            viewHolder.initView();
            return holder;
        }

        // 如果都没有，就抛出异常
        throw new RuntimeException("CommonHolder or HolderMapper(or register view type) must be configure at least one.");
    }

    @Override
    public int getItemViewType(int position) {
        if (mViewTypeMapper != null) {
            // 使用 Holder 的 class 对象的 hashCode 作为 viewType，简化 ViewType 使用的逻辑
            //noinspection unchecked
            Class<? extends CommonHolder<?>> holderClass =
                    mViewTypeMapper.getViewType(getItem(position), position);
            int viewType = getHolderViewType(holderClass);
            mViewTypeHolders.put(viewType, holderClass);
            return viewType;
        }
        if (mHolderMap != null) {
            T item = getItem(position);
            Class<? extends CommonHolder<?>> holderClass = mHolderMap.get(item.getClass());
            if (holderClass != null) {
                int viewType = getHolderViewType(holderClass);
                mViewTypeHolders.put(viewType, holderClass);
                return viewType;
            } else {
                throw new ViewTypeNotFountException(item);
            }
        }
        return super.getItemViewType(position);
    }

    private int getHolderViewType(Class<? extends CommonHolder<?>> holderClass) {
        return holderClass.hashCode();
    }

    <DataType> void registerViewType(Class<DataType> dataClass, Class<? extends CommonHolder<DataType>> holderClass) {
        mHolderMap.put(dataClass, holderClass);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(CommonRecyclerHolder<T> holder, int position) {
        CommonHolder<T> commonHolder = holder.getCommonHolder();
        if (commonHolder instanceof ComplexHolder) {
            //noinspection unchecked
            ((ComplexHolder) commonHolder).bindData(mData.get(position), position, this);
        } else {
            commonHolder.bindData(mData.get(position));
        }
        //noinspection unchecked
        bindListener(holder.itemView, position, (VH) commonHolder);
    }

    @SuppressWarnings("unused")
    public CommonAdapter<T, VH> addAll(Collection<? extends T> list) {
        mData.addAll(list);
        return this;
    }

    public CommonAdapter<T, VH> add(T t) {
        mData.add(t);
        return this;
    }

    @SuppressWarnings("unused")
    public CommonAdapter<T, VH> clear() {
        mData.clear();
        return this;
    }

    @SuppressWarnings("unused")
    public void remove(T t) {
        mData.remove(t);
    }

    @SuppressWarnings("unused")
    public void removeAll(Collection<? extends T> ts) {
        mData.removeAll(ts);
    }

    @Override
    public List<T> getData() {
        return mData;
    }

    private void bindListener(View parentView, final int position, final VH holder) {

        if (mItemClickListener != null) {
            parentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onClick(position, getItem(position));
                }
            });
        }

        if (mOnBindListener != null) {
            mOnBindListener.onBind(position, getItem(position), holder);
        }
    }

    public CommonAdapter<T, VH> setOnBindListener(OnBindListener<T, VH> listener) {
        mOnBindListener = listener;
        return this;
    }

    public CommonAdapter<T, VH> setOnItemClickListener(OnItemClickListener<T> listener) {
        mItemClickListener = listener;
        notifyDataSetChanged();
        return this;
    }

    public interface OnBindListener<T, VH> {
        void onBind(int position, T t, VH holder);
    }

    public interface OnItemClickListener<T> {
        void onClick(int position, T t);
    }

    @SuppressWarnings("WeakerAccess")
    public static class ViewTypeNotFountException extends IllegalStateException {
        public ViewTypeNotFountException(Object item) {
            super(String.format("没有注册 item %s 的 Holder 类型", item.getClass().getCanonicalName()));
        }
    }
}
