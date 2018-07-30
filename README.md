# Erlymon Common Adapter

## Contacts

Author - Sergey Penkovsky ([dev@erlymon.org](mailto:dev@erlymon.org))

Website - [https://www.erlymon.org](https://www.erlymon.org)

## Overview

Erlymon Common Adapter is an open source library.

## License

    Copyright 2018 Sergey Penkovsky sergey.penkovsky@gmail.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
## Docs

recycler-adapter - это универсальный адаптер для RecyclerView.
Основной для адаптера послужил код [DelegateAdapters Library](https://github.com/Liverm0r/DelegateAdapters).
Основное отличие от [DelegateAdapters Library](https://github.com/Liverm0r/DelegateAdapters), то что в текущей рализации
используется DataBinding.


Пример использования.

В скрипт gradle необходимо добавить репозиторий
```gradle
maven {url 'https://dl.bintray.com/erlymon/android'}
```
и саму библиотеку
```gradle
implementation 'org.erlymon.common:recycler-adapter:<version>'
```

В разметку activity добавить RecyclerView

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>
    <data>
        <import type="org.erlymon.common.common.Bindings"/>
        <variable
            name="model"
            type="org.erlymon.common.MainVewModel"/>
    </data>
    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/rv"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
            app:onSelectItem="@{(view, item) ->  model.traceLog(item)}"
            app:items="@{model.items}"
            />

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

А в activity

```kotlin
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainVewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainVewModel::class.java)
        binding.model = viewModel
    }
}
```

Также необходимо реализовать привязку данных к RecyclerView

```kotlin
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
```
