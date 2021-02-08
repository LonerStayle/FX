package kr.loner.fx.db.entity

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
    val replyList:List<Reply>? = null,
    val likeCountList:List<Long>? = null
) : Parcelable
