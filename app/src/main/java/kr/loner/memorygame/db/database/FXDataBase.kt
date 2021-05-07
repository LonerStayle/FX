package kr.loner.memorygame.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kr.loner.memorygame.db.dao.GameDao
import kr.loner.memorygame.db.dao.UserDao
import kr.loner.memorygame.db.entity.GameModel
import kr.loner.memorygame.db.entity.GameModelDateConverter
import kr.loner.memorygame.db.entity.UserData
import kr.loner.memorygame.db.entity.UserDataLikeListConverter


@Database(entities = [UserData::class, GameModel::class], version = 2)
@TypeConverters(UserDataLikeListConverter::class,GameModelDateConverter::class)
abstract class FXDataBase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val gameDao: GameDao

    companion object {
        @Volatile
        private var INSTANCE: FXDataBase? = null
        fun getInstance(context: Context): FXDataBase = synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context,
                FXDataBase::class.java,
                "FXDatabase"
            ).fallbackToDestructiveMigration().build().also {
                INSTANCE = it
            }
        }
    }
}
