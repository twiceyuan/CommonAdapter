package com.twiceyuan.commonadapter.library.util;

import android.view.View;

import com.twiceyuan.commonadapter.library.holder.CommonHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by twiceYuan on 1/20/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public class AdapterUtil {

    //Create a new ItemViewHolder using Java reflection
    public static CommonHolder createViewHolder(View view, Class<? extends CommonHolder> itemViewHolderClass) {
        try {
            Constructor<? extends CommonHolder> constructor = itemViewHolderClass.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to find a public constructor that takes an argument View in " +
                    itemViewHolderClass.getSimpleName(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getTargetException());
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to instantiate " + itemViewHolderClass.getSimpleName(),  e);
        }
    }

    //Parses the layout ID annotation form the itemViewHolderClass
    public static Integer parseItemLayoutId(Class<? extends CommonHolder> itemViewHolderClass) {
        Integer itemLayoutId = ClassAnnotationParser.getLayoutId(itemViewHolderClass);
        if (itemLayoutId == null) {
            throw new IllegalStateException("没找到布局");
        }
        return itemLayoutId;
    }
}
