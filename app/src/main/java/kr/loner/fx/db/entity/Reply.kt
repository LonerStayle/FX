package kr.loner.fx.db.entity

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize


@Parcelize
data class Reply(
    val idx:String?= null,
    val content:String?= null,
    val replyToReply:List<Reply>? =null,
    @ServerTimestamp
    val createAt:Timestamp? = null
):Parcelable
