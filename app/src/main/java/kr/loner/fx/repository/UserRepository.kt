package kr.loner.fx.repository

import kr.loner.fx.db.entity.UserData
import androidx.lifecycle.LiveData
import kr.loner.fx.db.dao.RoomDao

interface UserDataSource{
    fun getUser(): LiveData<UserData>
    fun update(user: UserData)


}


class UserRepository(private val roomDao:RoomDao):UserDataSource {

    override fun getUser(): LiveData<UserData>  = roomDao.getUser()

    override fun update(user: UserData) {
        roomDao.userUpdate(user)
    }


}