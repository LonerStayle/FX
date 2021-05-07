package kr.loner.memorygame.repository

import kr.loner.memorygame.db.entity.UserData
import androidx.lifecycle.LiveData
import kr.loner.memorygame.db.dao.UserDao

interface UserDataSource{
    fun getUser(): LiveData<UserData>
    fun update(user: UserData)


}


class UserRepository(private val userDao:UserDao):UserDataSource {

    override fun getUser(): LiveData<UserData>  = userDao.getUser()

    override fun update(user: UserData) {
        userDao.userUpdate(user)
    }


}