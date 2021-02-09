package kr.loner.fx.ui.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.firebase.Timestamp
import kr.loner.fx.db.entity.Reply
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:timeStampConverter")
fun timeStampConverter(tv: TextView,timestamp: Timestamp?){
    timestamp?:return
    SimpleDateFormat("yyyy년 MM월 DD일 a hh시 mm분", Locale.KOREAN).format(timestamp.toDate()).also {
        tv.text = it
    }
}
@BindingAdapter("app:getReplyCount")
fun getReplyCount(tv: TextView,list:List<Reply>?){
    if(list.isNullOrEmpty()){
        tv.text="0"
        return
    }
    var count = list.size
    for (reply in list) {
        if(!reply.replyToReply.isNullOrEmpty())
        count += reply.replyToReply.size
    }
    tv.text = count.toString()
}

//fun Timestamp.timeStampConverter():String {
//    return SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초", Locale.KOREAN).format(this.toDate())
//}
//
//fun Timestamp.timeStampSimple():String{
//    return SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREAN).format(this.toDate())
//}
//
//fun Timestamp.timeCompareTo():String{
//    val time = System.currentTimeMillis() - this.toDate().time
//    Date(time).also {
//        return SimpleDateFormat("mm분", Locale.KOREAN).format(it)
//    }