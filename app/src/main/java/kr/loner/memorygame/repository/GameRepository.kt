package kr.loner.memorygame.repository

import androidx.lifecycle.LiveData
import kr.loner.memorygame.db.dao.GameDao
import kr.loner.memorygame.db.entity.GameModel

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

