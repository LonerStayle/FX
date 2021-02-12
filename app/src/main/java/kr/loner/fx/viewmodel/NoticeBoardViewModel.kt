package kr.loner.fx.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.loner.fx.db.entity.NoticeBoard
import kr.loner.fx.db.entity.Reply
import kr.loner.fx.db.entity.UserData

class NoticeBoardViewModel() : ViewModel() {

    var noticeBoardIdx: String? = null
    var userData: UserData? = null
    var selectReply: Reply? = null
    private val db = FirebaseFirestore.getInstance()

    private val _noticedBoard = MutableLiveData<NoticeBoard>()
    val noticedBoard: LiveData<NoticeBoard>
        get() = _noticedBoard

    fun getNoticeBoard() {
        viewModelScope.launch(Dispatchers.IO) {
            db.collection("NoticeBoard").document(noticeBoardIdx!!).
                addSnapshotListener { value, _ ->
                    value ?: return@addSnapshotListener
                    _noticedBoard.postValue(value.toObject(NoticeBoard::class.java))
                }
        }
    }

    fun setReplyToReply(reply: Reply) {
        viewModelScope.launch(Dispatchers.IO) {

            val rootMap = mutableMapOf<String, Any>()
            val replyToReplyListMap = mutableMapOf<String, Any>()
            val replyListMap = mutableMapOf<String, Map<String, Any>>()
            val replyMap = mutableMapOf<String, Reply>()

            replyMap[reply.idx!!] = reply
            replyToReplyListMap["replyToReply"] = replyMap
            replyListMap[reply.parentIdx!!] = replyToReplyListMap
            rootMap["replyList"] = replyListMap

            db.collection("NoticeBoard").document(noticeBoardIdx!!)
                .set(rootMap.toMap(), SetOptions.merge())
        }
    }

    fun setReply(reply: Reply) {
        viewModelScope.launch(Dispatchers.IO) {
            val map = mutableMapOf<String, Map<String, Reply>>()
            val replyMap = mutableMapOf<String, Reply>()
            replyMap[reply.idx!!] = reply
            map["replyList"] = replyMap

            db.collection("NoticeBoard").document(noticeBoardIdx!!)
                .set(map.toMap(), SetOptions.merge())

        }
    }

    fun sendLike(myIdx: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.collection("NoticeBoard").document(noticeBoardIdx!!)
                .update("likeCountList", FieldValue.arrayUnion(myIdx))
        }
    }
    fun deleteLike(myIdx: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.collection("NoticeBoard").document(noticeBoardIdx!!)
                .update("likeCountList", FieldValue.arrayRemove(myIdx))
        }
    }
}