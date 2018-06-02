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
package org.erlymon.common.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseDelegateAdapter
        <T> implements IDelegateAdapter<T> {

    @LayoutRes
    abstract protected int getLayoutId();

    abstract protected void bind(ViewDataBinding binding, T item);

    @Override
    public void onRecycled(BaseViewHolder holder) {
    }

    @NonNull
    @Override
    public final BaseViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                getLayoutId(),
                parent,
                false);
        return new BaseViewHolder(binding);
    }

    @Override
    public final void onBindViewHolder(
            @NonNull BaseViewHolder holder,
            @NonNull List<T> items,
            int position) {
        bind(holder.getBinding(), items.get(position));
    }

}
