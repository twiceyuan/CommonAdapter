package com.twiceyuan.commonadapter.library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by twiceYuan on 1/20/16.
 *
 * @deprecated resId 在库项目中不是常量，不建议使用注解来配置
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface ViewId {
    int value();
}
