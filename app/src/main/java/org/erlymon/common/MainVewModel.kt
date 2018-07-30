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
package org.erlymon.common

import android.util.Log
import androidx.lifecycle.ViewModel
import org.erlymon.common.model.TextBean

class MainVewModel: ViewModel() {
    val items = MockDataFactory.prepareData()

    fun traceLog(item: Any) {
        when (item) {
            is TextBean -> {
                Log.w("MainViewModel", "TEXT BEAN: $item")
            }
        }
    }
}