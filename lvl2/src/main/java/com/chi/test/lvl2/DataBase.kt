package com.chi.test.lvl2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ModelStudent :: class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun studentDao() : StudentDAO

    companion object{

        @Volatile
        private var INSTANCE : DataBase? = null

        fun getDatabase(context: Context): DataBase {

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }

}