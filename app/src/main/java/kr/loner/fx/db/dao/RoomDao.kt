package kr.loner.fx.db.dao

import androidx.room.*
import androidx.lifecycle.LiveData
import kr.loner.fx.db.entity.NoticeBoard
import kr.loner.fx.db.entity.UserData

@Dao
interface RoomDao {


    @Query("SELECT * FROM UserData WHERE idx = 0")
    fun getUser():LiveData<UserData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun userUpdate(it: UserData)

}