package kr.loner.fx.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.loner.fx.db.entity.NoticeBoard
import kr.loner.fx.db.entity.Reply
import kr.loner.fx.db.entity.UserData

class NoticeBoardViewModel() : ViewModel() {

    var noticeBoardIdx: String? = null
    var userData: UserData? = null
    var selectReplyIdx: String? = null
    private val db = FirebaseFirestore.getInstance()

    private val _noticedBoard = MutableLiveData<NoticeBoard>()
    val noticedBoard: LiveData<NoticeBoard>
        get() = _noticedBoard

    fun getNoticeBoard() {
        viewModelScope.launch(Dispatchers.IO) {
            db.collection("NoticeBoard").document(noticeBoardIdx!!).get()
                .addOnSuccessListener {
                    it?:return@addOnSuccessListener
                    _noticedBoard.postValue(it.toObject(NoticeBoard::class.java))
                }
        }
    }

    fun setReplyToReply(reply: Reply) {
        viewModelScope.launch(Dispatchers.IO) {

            val field = FieldPath.of("replyList.${selectReplyIdx}.replyToReply")
            db.collection("NoticeBoard").document(noticeBoardIdx!!)
                .update(field,reply)
        }
    }

    fun setReply(reply: Reply) {
        viewModelScope.launch(Dispatchers.IO) {
            val field = FieldPath.of("replyList")
            db.collection("NoticeBoard").document(noticeBoardIdx!!).update(field, reply)
        }
    }

    fun sendLike(myIdx: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.collection("NoticeBoard").document(noticeBoardIdx!!)
                .update("likeCountList", FieldValue.arrayUnion(myIdx))
        }
    }
}