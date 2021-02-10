package kr.loner.fx.repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import kr.loner.fx.db.dao.GameDao
import kr.loner.fx.db.entity.GameModel

interface GameDataSource {
    fun getAllList(): LiveData<List<GameModel>>

    fun insert(gameModel: GameModel)
}
class GameRepository(private val gameDao:GameDao):GameDataSource{
    override fun getAllList(): LiveData<List<GameModel>> {
        return gameDao.getAllList()
    }

    override fun insert(gameModel: GameModel) {
        return gameDao.insert(gameModel)
    }

}

