package kr.loner.fx.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.loner.fx.db.dao.RoomDao
import kr.loner.fx.repository.UserRepository
import kr.loner.fx.viewmodel.MainViewModel
import kr.loner.fx.viewmodel.NoticeBoardViewModel

class ViewModelFactory (
    userDataSource: RoomDao
    ):ViewModelProvider.Factory{
    private val userRepository = UserRepository(userDataSource)
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(MainViewModel::class.java)->{
                return  MainViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(NoticeBoardViewModel::class.java)->{
                return  NoticeBoardViewModel() as T
            }
        }
        throw IllegalAccessException("Unknown ViewModel")

    }

}
