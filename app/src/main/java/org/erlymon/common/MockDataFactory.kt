/**
 * Copyright 2018 Sergey Penkovsky sergey.penkovsky@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.erlymon.common

import org.erlymon.common.model.CheckBean
import org.erlymon.common.model.TextBean

import java.util.ArrayList
import java.util.Random

internal object MockDataFactory {

    private val SIZE = 20

    fun prepareData(): List<Any> {
        val objects = ArrayList<Any>(SIZE)
        val random = Random()
        for (i in 0 until SIZE) {
            val item: Any
            val type = random.nextInt(3)
            if (type == 0) {
                item = TextBean("Title $i", "Description $i")
                //} else if (type == 1) {
                //    item = new ImageViewModel("Title " + i, R.mipmap.ic_launcher_round);
            } else {
                item = CheckBean("You still love this lib", true)
            }
            objects.add(item)
        }
        return objects
    }
}