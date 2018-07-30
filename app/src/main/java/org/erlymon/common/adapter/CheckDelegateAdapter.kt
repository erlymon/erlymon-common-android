/**
 * Copyright 2018 Sergey Penkovsky sergey.penkovsky@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.erlymon.common.adapter

import androidx.databinding.ViewDataBinding

import org.erlymon.common.model.CheckBean

import org.erlymon.common.R
import org.erlymon.common.databinding.CheckItemBinding

class CheckDelegateAdapter : BaseDelegateAdapter<CheckBean>() {

    override fun getLayoutId(): Int {
        return R.layout.check_item
    }

    override fun bind(binding: ViewDataBinding, item: CheckBean) {
        if (binding is CheckItemBinding) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun isForViewType(items: List<*>, position: Int): Boolean {
        return items[position] is CheckBean
    }
}
