package kr.loner.memorygame.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.lifecycle.LiveData
import kr.loner.memorygame.db.entity.GameModel

@Dao
interface GameDao {

    @Query("SELECT*FROM GameModel")
    fun getAllList():LiveData<List<GameModel>>

    @Insert
    fun insert(gameModel: GameModel)

}