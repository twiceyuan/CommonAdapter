package com.twiceyuan.commonadapter.library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewId {
    int value();
}
