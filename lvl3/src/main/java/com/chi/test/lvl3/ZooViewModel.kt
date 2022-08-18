package com.chi.test.lvl3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chi.test.lvl3.connection.ResultConnection
import com.chi.test.lvl3.models.ModelZooItem
import com.chi.test.lvl3.room.DataBase
import kotlinx.coroutines.launch

class ZooViewModel(application: Application) : AndroidViewModel(application) {
    private val dataBase = DataBase.getDatabase(application.applicationContext)
    private val connection = ResultConnection(application.applicationContext)
    private val repository = ApiRepo()
    var zooItem = MutableLiveData<ArrayList<ModelZooItem>>()

    fun getZoo() {
        viewModelScope.launch {
            if (connection.isNetworkConnected()) {
                zooItem.value = repository.getZooItem().body()?.toCollection(ArrayList())
                zooItem.value.let {
                    dataBase.zooDao().clearDb()
                }
                zooItem.value?.forEach {
                    dataBase.zooDao().insertToDB(it)
                }
            } else {
                zooItem.value = dataBase.zooDao().getAll().toCollection(ArrayList())
            }
        }
    }

    fun updateDb(zooItem: ModelZooItem) {
        viewModelScope.launch {
            dataBase.zooDao().update(zooItem.id ?: 0,zooItem.favorite)
        }
    }

    fun updatePaging() {
        viewModelScope.launch {
            if (connection.isNetworkConnected()) {
                zooItem.value?.addAll(repository.getZooItem().body()?.toCollection(ArrayList()) ?: listOf(ModelZooItem(null,"no info","np info","no info", false)))
                repository.getZooItem().body()?.toCollection(ArrayList())?.forEach {
                    dataBase.zooDao().insertToDB(it)
                }
            }
        }
    }
}