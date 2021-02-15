package kr.loner.fx.ui.util

import android.widget.NumberPicker
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.firebase.Timestamp
import kr.loner.fx.db.entity.Reply
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:timeStampConverter")
fun timeStampConverter(tv: TextView,timestamp: Timestamp?){
    timestamp?:return
    SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분", Locale.KOREAN).format(timestamp.toDate()).also {
        tv.text = it
    }
}

@BindingAdapter("app:dateTimeStampConverter")
fun dateTimeStampConverter(tv: TextView,timestamp: Date?){
    timestamp?:return
    SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREAN).format(timestamp).also {
        tv.text = it
    }
}
@BindingAdapter("app:getReplyCount")
fun getReplyCount(tv: TextView,list:HashMap<String,Reply>?){
    if(list.isNullOrEmpty()){
        tv.text="0"
        return
    }
    var count = list.size
    for (reply in list.values) {
        if(!reply.replyToReply.isNullOrEmpty())
        count += reply.replyToReply.size
    }
    tv.text = count.toString()
}

@BindingAdapter("minValue")
fun minValue(view: NumberPicker, min:Int) {view.minValue = min}


@BindingAdapter("maxValue")
fun maxValue(view: NumberPicker, max:Int) {view.maxValue = max}



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