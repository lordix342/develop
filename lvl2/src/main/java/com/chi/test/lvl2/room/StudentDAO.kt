package com.chi.test.lvl2.room

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface StudentDAO {
    @Query("SELECT * FROM TestName")
    fun getAll() : Flowable<Array<ModelStudent>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToDB(student: ModelStudent):Completable

    @Query("UPDATE TestName SET isStudent = :isStudent WHERE id =:id")
    fun update(id: Int, isStudent: Boolean):Completable

    @Query("DELETE FROM TestName")
    fun clearDb()

    @Delete
    fun delete(student: ModelStudent)
}