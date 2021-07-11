package com.twiceyuan.commonadapter.library.util;

/**
 * Created by twiceYuan on 1/21/16.
 */
public class LayoutNotFoundException extends IllegalStateException {

    public LayoutNotFoundException() {
        super("please provide Layout Id in your viewHolder with @LayoutId.");
    }
}
