package com.twiceyuan.commonadapter.library.adapter;

import java.util.Collection;
import java.util.List;

/**
 * Created by twiceYuan on 1/20/16.
 */
@SuppressWarnings("unused")
public interface DataManager<T> {
    DataManager<T> clear();
    DataManager<T> add(T t);
    DataManager<T> addAll(Collection<? extends T> list);
    void remove(T t);
    void removeAll(Collection<? extends T> ts);
    List<T> getData();
}
