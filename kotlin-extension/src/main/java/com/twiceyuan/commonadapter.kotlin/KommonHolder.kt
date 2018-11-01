package com.twiceyuan.commonadapter.kotlin

import android.view.View
import com.twiceyuan.commonadapter.library.holder.CommonHolder

abstract class KommonHolder<T> : CommonHolder<T>() {
    override fun bindData(t: T) {
        itemView.bindData(t)
    }

    abstract fun View.bindData(t: T)
}