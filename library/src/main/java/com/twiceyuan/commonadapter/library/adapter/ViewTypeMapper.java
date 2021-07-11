package com.twiceyuan.commonadapter.library.adapter;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;

/**
 * Created by twiceYuan on 3/4/16.
 * <p/>
 * 描述实体 => CommonHolder 的关系映射，来简化 ViewType 逻辑
 */
public interface ViewTypeMapper<Type> {

    /**
     * 提供从实体或者 position 到 viewType 的映射
     *
     * @param item     实体
     * @param position item 所在的位置
     * @return 该位置所使用的 viewHolder（建议在 Holder 中定义 public 常量）
     */
    Class<? extends CommonHolder<? extends Type>> getViewType(Type item, int position);
}
