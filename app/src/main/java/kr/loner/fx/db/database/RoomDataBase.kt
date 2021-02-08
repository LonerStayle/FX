package kr.loner.fx.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kr.loner.fx.db.dao.RoomDao


@Database(entities = [Class::class],version = 1)
abstract class RoomDataBase : RoomDatabase() {
    abstract val itDao: RoomDao

    companion object{
        @Volatile
        private var INSTANCE:RoomDataBase? = null
        fun getInstance(context: Context):RoomDataBase = synchronized(this){
            INSTANCE?:Room.databaseBuilder(
                context,
                RoomDataBase::class.java,
                "Room_DataBase"
            ).fallbackToDestructiveMigration().build().also {
                INSTANCE = it
            }
        }
    }
//    companion object {
//        @Volatile
//        private var INSTANCE: RoomDataBase? = null
//        fun getInstance(context: Context): RoomDataBase = synchronized(this) {
//            INSTANCE ?: Room.databaseBuilder(
//                context,
//                RoomDataBase::class.java,
//                "Room_DataBase"
//            ).fallbackToDestructiveMigration()
//                .build().also {
//                    INSTANCE = it
//                }
//        }
//    }
}