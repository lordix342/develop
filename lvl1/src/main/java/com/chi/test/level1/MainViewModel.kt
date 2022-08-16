package com.chi.test.level1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var counter = MutableLiveData<Int>(0)

    fun counter() {
        counter.value = counter.value?.plus(1)
    }
}