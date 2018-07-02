package com.twiceyuan.commonadapter.kotlin

import android.content.Context
import com.twiceyuan.commonadapter.library.adapter.CommonAdapter
import com.twiceyuan.commonadapter.library.adapter.CommonListAdapter
import com.twiceyuan.commonadapter.library.adapter.MultiTypeAdapter
import com.twiceyuan.commonadapter.library.adapter.ViewTypeMapper
import com.twiceyuan.commonadapter.library.adapter.WrapperAdapter
import com.twiceyuan.commonadapter.library.holder.CommonHolder

inline fun <reified Model, reified ModelHolder : CommonHolder<Model>> Context.buildRvAdapter() =
        CommonAdapter<Model, ModelHolder>(this, ModelHolder::class.java)

inline fun <reified Model, reified ModelHolder : CommonHolder<Model>> Context.buildListAdapter() =
        CommonListAdapter<Model, ModelHolder>(this, ModelHolder::class.java)

fun Context.buildMultiTypeAdapter(viewTypeMapper: ViewTypeMapper) =
        MultiTypeAdapter(this, viewTypeMapper)

inline fun <reified Model, reified ModelHolder : CommonHolder<Model>> Context.buildWrapperAdapter() =
        WrapperAdapter<Model, ModelHolder>(this, ModelHolder::class.java)

