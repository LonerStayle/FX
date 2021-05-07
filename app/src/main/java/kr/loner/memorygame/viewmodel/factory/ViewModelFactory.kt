package kr.loner.memorygame.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.loner.memorygame.db.dao.GameDao
import kr.loner.memorygame.db.dao.UserDao
import kr.loner.memorygame.repository.GameRepository
import kr.loner.memorygame.repository.UserRepository
import kr.loner.memorygame.viewmodel.GameViewModel
import kr.loner.memorygame.viewmodel.MainViewModel
import kr.loner.memorygame.viewmodel.NoticeBoardViewModel

class ViewModelFactory (
    userDataSource: UserDao,
    gameDataSource: GameDao
    ):ViewModelProvider.Factory{
    private val userRepository = UserRepository(userDataSource)
    private val gameRepository = GameRepository(gameDataSource)
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(MainViewModel::class.java)->{
                return  MainViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(NoticeBoardViewModel::class.java)->{
                return  NoticeBoardViewModel() as T
            }
            modelClass.isAssignableFrom(GameViewModel::class.java)->{
                return  GameViewModel(gameRepository) as T
            }
        }
        throw IllegalAccessException("Unknown ViewModel")

    }

}
