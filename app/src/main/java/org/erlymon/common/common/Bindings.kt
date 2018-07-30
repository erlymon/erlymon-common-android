package org.erlymon.common.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.erlymon.common.adapter.CheckDelegateAdapter
import org.erlymon.common.adapter.CompositeDelegateAdapter
import org.erlymon.common.adapter.OnItemListener
import org.erlymon.common.adapter.TextDelegateAdapter

object Bindings {
    @BindingAdapter(value = ["items", "onSelectItem"], requireAll = false)
    @JvmStatic fun RecyclerView.bindData(items: List<Any>, listener: OnItemListener) {
        items?.apply {
            if (adapter == null) {
                adapter = CompositeDelegateAdapter.Builder<Any>()
                        .add(TextDelegateAdapter(listener))
                        .add(CheckDelegateAdapter())
                        .build()
            }
            (adapter as CompositeDelegateAdapter<Any>).swapData(this)
        }
    }
}