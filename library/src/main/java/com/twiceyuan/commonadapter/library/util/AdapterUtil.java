/*
 Copyright 2014 Ribot Ltd.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.twiceyuan.commonadapter.library.util;

import android.view.View;

import com.twiceyuan.commonadapter.library.Singleton;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdapterUtil {

    //Create a new ItemViewHolder using Java reflection
    public static CommonHolder createViewHolder(View view, Class<? extends CommonHolder> itemViewHolderClass) {
        //noinspection TryWithIdenticalCatches
        try {
            CommonHolder holder = itemViewHolderClass.newInstance();
            holder.setItemView(view);
            return holder;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    //Parses the layout ID annotation form the itemViewHolderClass
    public static Integer parseItemLayoutId(Class<? extends CommonHolder> itemViewHolderClass) {
        Integer itemLayoutId = ClassAnnotationParser.getLayoutId(itemViewHolderClass);
        if (itemLayoutId == null) {
            throw new LayoutNotFoundException();
        }
        return itemLayoutId;
    }

    /**
     * Init singleton object of adapter that defined in common holder
     */
    public static synchronized void setupAdapterSingleton(Map<String, Object> singletonCache, CommonHolder holder) {

        Field[] declaredFields = holder.getClass().getDeclaredFields();

        List<Field> singletonFiled = new ArrayList<>();

        for (Field field : declaredFields) {
            if (field.getAnnotation(Singleton.class) != null) {
                singletonFiled.add(field);
            }
        }

        // is need to init
        if (singletonCache.size() == 0) {
            holder.initSingleton();
            for (Field field : singletonFiled) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                try {
                    Object o = field.get(holder);
                    singletonCache.put(field.getName(), o);
                } catch (IllegalAccessException ignored) {
                    ignored.printStackTrace();
                } finally {
                    field.setAccessible(accessible);
                }
            }
        } else {
            for (Field field : singletonFiled) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                Object cachedObject = singletonCache.get(field.getName());
                if (cachedObject != null) {
                    try {
                        field.set(holder, cachedObject);
                    } catch (IllegalAccessException ignored) {
                        ignored.printStackTrace();
                    } finally {
                        field.setAccessible(accessible);
                    }
                }
            }
        }
    }
}
