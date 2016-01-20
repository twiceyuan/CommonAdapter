package com.twiceyuan.commonadapter.library.adapter;

import java.util.Collection;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public interface Adapter<T> {
    void addAll(Collection<T> list);
    void add(T t);
    void clear();
    void remove(T t);
    void removeAll(Collection<T> ts);
}
