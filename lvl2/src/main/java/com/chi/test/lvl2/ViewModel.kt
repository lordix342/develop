package com.chi.test.lvl2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chi.test.lvl2.room.DataBase
import com.chi.test.lvl2.room.ModelStudent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.newThread
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {
    var list = MutableLiveData<ArrayList<ModelStudent>>()
    private val dataBase = DataBase.getDatabase(application.applicationContext)
    var student = MutableLiveData<ModelStudent>()
    var successInsert = MutableLiveData(false)

    fun readDb() {
        viewModelScope.launch {
            dataBase.studentDao().getAll()
                .subscribeOn(newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    list.value = it.toCollection(ArrayList())
                }, {
                    Log.d("error","error $it")
                }, {

                })
        }
    }

    fun updateToDb(student: ModelStudent) {
        viewModelScope.launch {
            dataBase.studentDao().update(student.id ?: 0,student.isStudent)
                .subscribeOn(newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                    Log.d("error","error $it")
                })
        }
    }

    fun student(studentUp: ModelStudent) {
        student.value = studentUp
    }

    fun insertStudent(student: ModelStudent) {
        viewModelScope.launch {
            dataBase.studentDao().insertToDB(student)
                .subscribeOn(newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           successInsert.value = true
                }, {
                    Log.d("error","error $it")
                })
        }
    }

    fun deleteStudent(student: ModelStudent) {
        viewModelScope.launch {
            dataBase.studentDao().delete(student)
        }
    }
}