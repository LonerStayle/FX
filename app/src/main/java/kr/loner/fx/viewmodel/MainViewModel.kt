package kr.loner.fx.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.loner.fx.db.entity.NoticeBoard
import kr.loner.fx.db.entity.Reply
import kr.loner.fx.db.entity.UserData

import kr.loner.fx.repository.UserRepository

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {


    private val db = FirebaseFirestore.getInstance()

    val userData: LiveData<UserData>
        get() = userRepository.getUser()

    private val _noticeBoardList = MutableLiveData<List<NoticeBoard>>()
    val noticeBoardList: LiveData<List<NoticeBoard>>
        get() = _noticeBoardList


    fun userDataUpdate(user: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.update(user)
        }
    }

    fun getNoticeBoardList() {
        db.collection("NoticeBoard").get().addOnSuccessListener {
            _noticeBoardList.postValue(it.toObjects(NoticeBoard::class.java).toList())
        }
    }
    fun setNoticeBoard(randomId:String,noticeBoard: NoticeBoard){
        db.collection("NoticeBoard").document(randomId).set(noticeBoard)
    }
}
