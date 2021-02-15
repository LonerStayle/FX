package kr.loner.fx.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.loner.fx.db.entity.NoticeBoard
import kr.loner.fx.db.entity.UserData
import kr.loner.fx.repository.UserRepository

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {


    private val db = FirebaseFirestore.getInstance()
     var name: String? = null

    val userData: LiveData<UserData>
        get() = userRepository.getUser()

    private val _noticeBoardList = MutableLiveData<List<NoticeBoard>>()
    val noticeBoardList: LiveData<List<NoticeBoard>>
        get() = _noticeBoardList


    fun userNameCheck(user: UserData, isSuccess: (UserData) -> Unit, isFelid: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            db.collection("User").whereEqualTo("name", user.name).get()
                .addOnSuccessListener {
                    if (it.size() == 0)
                        isSuccess(user)
                    else
                        isFelid()
                }
        }
    }

    fun userDataInsert(user: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            val updateMap: MutableMap<String, Any> = HashMap()
            updateMap["name"] = user.name!!
            db.collection("User").document("name").set(updateMap).addOnSuccessListener {
                viewModelScope.launch(Dispatchers.IO) {
                    userRepository.update(user)
                }
            }
        }
    }

    fun getNoticeBoardList() {
        db.collection("NoticeBoard").addSnapshotListener { value, _ ->
            _noticeBoardList.postValue(value?.toObjects(NoticeBoard::class.java)?.toList())
        }
    }

    fun setNoticeBoard(randomId: String, noticeBoard: NoticeBoard) {
        db.collection("NoticeBoard").document(randomId).set(noticeBoard)
    }


}
