package com.twiceyuan.commonadapter.kotlin

import android.content.Context
import com.twiceyuan.commonadapter.library.adapter.*
import com.twiceyuan.commonadapter.library.holder.CommonHolder

inline fun <reified Model, reified ModelHolder : CommonHolder<Model>> Context.buildRvAdapter() =
        CommonAdapter<Model, ModelHolder>(this, ModelHolder::class.java)

inline fun <reified Model, reified ModelHolder : CommonHolder<Model>> Context.buildListAdapter() =
        CommonListAdapter<Model, ModelHolder>(this, ModelHolder::class.java)

inline fun <reified Model> Context.buildMultiTypeAdapter(viewTypeMapper: ViewTypeMapper<Model>) =
        MultiTypeAdapter<Model>(this, viewTypeMapper)

inline fun <reified Model, reified ModelHolder : CommonHolder<Model>> Context.buildWrapperAdapter() =
        WrapperAdapter<Model, ModelHolder>(this, ModelHolder::class.java)

