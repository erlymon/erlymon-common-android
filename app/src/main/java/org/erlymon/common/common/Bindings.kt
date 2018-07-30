package org.erlymon.common.common

import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.erlymon.common.adapter.CheckDelegateAdapter
import org.erlymon.common.adapter.CompositeDelegateAdapter
import org.erlymon.common.adapter.OnItemListener
import org.erlymon.common.adapter.TextDelegateAdapter
import org.erlymon.common.model.TextBean

object Bindings {
    @BindingAdapter(value = ["items", "onSelectItem"], requireAll = false)
    @JvmStatic fun RecyclerView.bindData(items: List<Any>, listener: OnItemListener) {
        items?.apply {
            if (adapter == null) {
                adapter = CompositeDelegateAdapter.Builder<Any>()
                        /*.add(TextDelegateAdapter(object: OnItemListener {
                            override fun onClick(view: View, item: Any) {
                                when (item) {
                                    is TextBean -> {
                                        Toast.makeText(view.context, "TEXT BEAN: $item", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        }))*/
                        .add(TextDelegateAdapter(listener))
                        .add(CheckDelegateAdapter())
                        .build()
            }
            (adapter as CompositeDelegateAdapter<Any>).swapData(this)
        }
    }
}