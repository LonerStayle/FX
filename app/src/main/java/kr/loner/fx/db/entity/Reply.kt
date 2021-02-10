package kr.loner.fx.db.entity

import android.os.Parcelable
import androidx.room.TypeConverters
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize


@Parcelize
data class Reply(
    val idx:String?= null,
    val writeName:String? = null,
    val content:String?= null,
    val replyToReply:List<Reply>? =null,
    val likeCountList:List<Long>?=null,
    @ServerTimestamp
    val timestamp:Timestamp? = null
):Parcelable
