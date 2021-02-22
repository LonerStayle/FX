package kr.grandoption.fx.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.grandoption.fx.db.dao.GameDao
import kr.grandoption.fx.db.dao.UserDao
import kr.grandoption.fx.repository.GameRepository
import kr.grandoption.fx.repository.UserRepository
import kr.grandoption.fx.viewmodel.GameViewModel
import kr.grandoption.fx.viewmodel.MainViewModel
import kr.grandoption.fx.viewmodel.NoticeBoardViewModel

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
