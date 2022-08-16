package com.chi.test.lvl2

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface StudentDAO {
    @Query("SELECT * FROM TestName")
    fun getAll() : Flowable<Array<ModelStudent>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToDB(student: ModelStudent):Completable

    @Update
    fun update(student: ModelStudent):Completable

    @Query("DELETE FROM TestName")
    fun clearDb()
}