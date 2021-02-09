package kr.loner.fx.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.loner.fx.db.dao.RoomDao
import kr.loner.fx.repository.UserRepository
import kr.loner.fx.viewmodel.MainViewModel

class ViewModelFactory (
    userDataSource: RoomDao
    ):ViewModelProvider.Factory{
    private val userRepository = UserRepository(userDataSource)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(MainViewModel::class.java)->{
                @Suppress("unchecked_cast")
                return  MainViewModel(userRepository) as T
            }
        }
        throw IllegalAccessException("Unknown ViewModel")

    }

}
