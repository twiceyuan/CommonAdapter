package com.twiceyuan.commonadapter.library.util;

import com.twiceyuan.commonadapter.library.LayoutId;

import java.lang.annotation.Annotation;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public class ClassAnnotationParser {

    /**
     * Parse {@link LayoutId} annotation form a class
     *
     * @param myClass class that is annotated
     * @return the integer value of the annotation
     */
    public static Integer getLayoutId(Class myClass) {
        Annotation annotation = myClass.getAnnotation(LayoutId.class);

        if (annotation instanceof LayoutId) {
            LayoutId layoutIdAnnotation = (LayoutId) annotation;
            return layoutIdAnnotation.value();
        }

        return null;
    }
}
