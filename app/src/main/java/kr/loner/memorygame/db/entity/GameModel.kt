package kr.loner.memorygame.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity
@Parcelize
data class GameModel(
    @PrimaryKey(autoGenerate = true)
    val id:Long= 0L,
    val round:Int?=null,
    val row:Int?=null,
    val col:Int?=null,
    val myName:String?=null,
    val score:Int?=null,
    @TypeConverters(GameModelDateConverter::class)
    val timeStamp: Date? = null
):Parcelable