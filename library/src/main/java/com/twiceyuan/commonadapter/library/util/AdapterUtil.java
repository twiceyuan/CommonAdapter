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

import com.twiceyuan.commonadapter.library.holder.CommonHolder;

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
}
