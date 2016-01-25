package com.twiceyuan.commonadapter.library.adapter;

import java.util.Collection;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public interface DataManager<T> {
    void clear();
    void add(T t);
    void addAll(Collection<T> list);
    void remove(T t);
    void removeAll(Collection<T> ts);
}
