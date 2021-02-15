package kr.loner.fx.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class GameModel(
    @PrimaryKey(autoGenerate = true)
    val id:Long= 0L,
    val round:Int,
    val row:Int,
    val col:Int,
    val myName:String,
    val score:Int,
    @ServerTimestamp
    val timeStamp:Timestamp? = null
):Parcelable