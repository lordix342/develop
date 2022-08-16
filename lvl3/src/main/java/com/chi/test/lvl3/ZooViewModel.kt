package com.chi.test.lvl3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chi.test.lvl3.models.ModelZooItem
import kotlinx.coroutines.launch

class ZooViewModel : ViewModel() {
    private val repository = ApiRepo()
    var zooItem = MutableLiveData<ArrayList<ModelZooItem>>()

    fun getZoo() {
        viewModelScope.launch {
            zooItem.value = repository.getZooItem().body()?.toCollection(ArrayList())
        }
    }
}