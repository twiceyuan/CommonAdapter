package com.twiceyuan.commonadapter.kotlin

import android.view.View
import com.twiceyuan.commonadapter.library.holder.CommonHolder

abstract class KommonHolder<T> : CommonHolder<T>() {

    override fun bindData(data: T) {
        itemView.bindData(data)
    }

    abstract fun View.bindData(data: T)
}