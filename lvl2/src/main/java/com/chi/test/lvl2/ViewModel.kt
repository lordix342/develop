package com.chi.test.lvl2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.newThread
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {
    var list = MutableLiveData<ArrayList<ModelStudent>>()
    val dataBase = DataBase.getDatabase(application.applicationContext)
    var student = MutableLiveData<ModelStudent>()
    val listOfStudent = arrayListOf(
        ModelStudent(null, "asdfgs", 12, false),
        ModelStudent(null, "asdfgs", 12, false),
        ModelStudent(null, "asdfgs", 12, false),
        ModelStudent(null, "asdfgs", 12, false),
        ModelStudent(null, "asdfgs", 12, false),
        ModelStudent(null, "asdfgs", 12, false),
        ModelStudent(null, "asdfgs", 12, false)
    )

    init {
        saveToDb()
    }

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
    fun saveToDb() {
        viewModelScope.launch {
            listOfStudent.forEach {
                dataBase.studentDao().insertToDB(it)
                    .subscribeOn(newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                    }, {
                        Log.d("error","error $it")
                    })
            }
        }
    }
    fun updateToDb(student: ModelStudent) {
        viewModelScope.launch {
            dataBase.studentDao().insertToDB(student)
                .subscribeOn(newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                    Log.d("error","error $it")
                })
        }
    }

    fun student(studentUp:ModelStudent) {
        student.value = studentUp
    }
}