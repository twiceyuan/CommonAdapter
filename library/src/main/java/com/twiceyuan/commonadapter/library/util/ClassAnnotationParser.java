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

import com.twiceyuan.commonadapter.library.LayoutId;

import java.lang.annotation.Annotation;

public class ClassAnnotationParser {

    /**
     * Parse {@link LayoutId} annotation form a class
     *
     * @param myClass class that is annotated
     * @return the integer value with the annotation
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
