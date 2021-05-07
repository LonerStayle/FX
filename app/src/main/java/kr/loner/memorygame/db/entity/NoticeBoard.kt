package kr.loner.memorygame.db.entity

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoticeBoard(
    val idx:String? = null,
    val name:String? = null,
    val title:String? = null,
    val content:String? = null,
    @ServerTimestamp
    val timestamp:Timestamp? = null,
    val replyList:HashMap<String,Reply>? = hashMapOf<String,Reply>(),
    val likeCountList:List<String>? = null
) : Parcelable
