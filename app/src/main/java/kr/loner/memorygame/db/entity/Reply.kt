package kr.loner.memorygame.db.entity

import android.os.Parcelable

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize


@Parcelize
data class Reply(
    val idx:String?= null,
    var parentIdx:String?="0000",
    val writeName:String? = null,
    val content:String?= null,
    val replyToReply:HashMap<String,Reply>? =null,
    val likeCountList:List<Long> = listOf(0L),
    @ServerTimestamp
    val timestamp:Timestamp? = null
):Parcelable
