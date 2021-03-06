package kr.loner.memorygame.db.dao

import androidx.room.*
import androidx.lifecycle.LiveData
import kr.loner.memorygame.db.entity.UserData

@Dao
interface UserDao {

    @Query("SELECT * FROM UserData WHERE idx = 0")
    fun getUser():LiveData<UserData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun userUpdate(user: UserData)

}