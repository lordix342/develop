package com.chi.test.lvl3.room

import androidx.room.*
import com.chi.test.lvl3.models.ModelZooItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface ZooDAO {
    @Query("SELECT * FROM ZooItem")
    fun getAll() : Array<ModelZooItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToDB(zooItem: ModelZooItem):Completable

    @Query("UPDATE ZooItem SET favorite = :favorite WHERE id =:id")
    fun update(id: Int, favorite: Boolean)

    @Query("DELETE FROM ZooItem")
    fun clearDb()
}