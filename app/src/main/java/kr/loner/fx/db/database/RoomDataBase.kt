package kr.loner.fx.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kr.loner.fx.db.dao.RoomDao
import kr.loner.fx.db.entity.UserData
import kr.loner.fx.db.entity.UserDataLikeListConverter


@Database(entities = [UserData::class],version = 2)
@TypeConverters(UserDataLikeListConverter::class)
abstract class RoomDataBase : RoomDatabase() {
    abstract val userDao: RoomDao

    companion object{
        @Volatile
        private var INSTANCE:RoomDataBase? = null
        fun getInstance(context: Context):RoomDataBase = synchronized(this){
            INSTANCE?:Room.databaseBuilder(
                context,
                RoomDataBase::class.java,
                "UserDatabase"
            ).fallbackToDestructiveMigration().build().also {
                INSTANCE = it
            }
        }
    }
}
