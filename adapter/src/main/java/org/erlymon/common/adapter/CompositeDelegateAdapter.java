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

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CompositeDelegateAdapter<T>
        extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int FIRST_VIEW_TYPE = 0;

    private final SparseArray<IDelegateAdapter> typeToAdapterMap;
    protected final @NonNull
    List<T> data = new ArrayList<>();

    private CompositeDelegateAdapter(
            @NonNull SparseArray<IDelegateAdapter> typeToAdapterMap) {
        this.typeToAdapterMap = typeToAdapterMap;
    }

    @Override
    public final int getItemViewType(int position) {
        for (int i = FIRST_VIEW_TYPE; i < typeToAdapterMap.size(); i++) {
            final IDelegateAdapter delegate = typeToAdapterMap.valueAt(i);
            //noinspection unchecked
            if (delegate.isForViewType(data, position)) {
                return typeToAdapterMap.keyAt(i);
            }
        }
        throw new NullPointerException(
                "Can not get viewType for position " + position);
    }

    @Override
    public final BaseViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        return typeToAdapterMap.get(viewType)
                .onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(
            @NonNull BaseViewHolder holder, int position) {
        final IDelegateAdapter delegateAdapter =
                typeToAdapterMap.get(getItemViewType(position));
        if (delegateAdapter != null) {
            //noinspection unchecked
            delegateAdapter.onBindViewHolder(holder, data, position);
        } else {
            throw new NullPointerException(
                    "can not find adapter for position " + position);
        }
    }

    @Override
    public void onViewRecycled(BaseViewHolder holder) {
        //noinspection unchecked
        typeToAdapterMap.get(holder.getItemViewType())
                .onRecycled(holder);
    }

    public void swapData(@NonNull List<T> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public final int getItemCount() {
        return data.size();
    }

    public static class Builder<T> {

        private int count;
        private final SparseArray<IDelegateAdapter> typeToAdapterMap;

        public Builder() {
            typeToAdapterMap = new SparseArray<>();
        }

        public Builder<T> add(
                @NonNull IDelegateAdapter<? extends T> delegateAdapter) {
            typeToAdapterMap.put(count++, delegateAdapter);
            return this;
        }

        public CompositeDelegateAdapter<T> build() {
            if (count == 0) {
                throw new IllegalArgumentException("Register at least one adapter");
            }
            return new CompositeDelegateAdapter<>(typeToAdapterMap);
        }
    }
}
